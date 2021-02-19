package com.changing.bg.framework.configure.security;

import com.alibaba.fastjson.JSON;
import com.changing.bg.model.entity.oauth.OauthClientDetailsDO;
import com.changing.bg.model.entity.oauth.OauthCodeDO;
import com.changing.bg.model.entity.user.UserDO;
import com.changing.bg.model.entity.user.UserPermissionDO;
import com.changing.bg.reposity.oauth.OauthClientDetailsRepository;
import com.changing.bg.reposity.oauth.OauthCodeRepository;
import com.changing.bg.reposity.user.UserPermissionReposity;
import com.changing.bg.reposity.user.UserReposity;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class SecurityBeanConfiguration {

    @Autowired
    private UserReposity userReposity;
    @Autowired
    private UserPermissionReposity userPermissionReposity;

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        return new SecurityTokenStore();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserDO userParam = new UserDO();
            userParam.setUserName(username);
            UserDO dbUser = userReposity.getUser(userParam);
            if (null == dbUser) {
                throw new IllegalArgumentException("用户 " + username + " 不存在");
            }

            UserPermissionDO userPermissionParam = new UserPermissionDO();
            userPermissionParam.setUserId(dbUser.getId());
            List<UserPermissionDO> permissionList = userPermissionReposity.listUserPermissions(userPermissionParam);
            if (null != permissionList && !permissionList.isEmpty()) {
                Set<Integer> perms = permissionList.stream()
                        .map(UserPermissionDO::getPermissionId).collect(Collectors.toSet());
                List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(perms.toArray(new String[]{}));
                return new User(dbUser.getUserName(), dbUser.getPassword(), authorities);
            } else {
                //TODO
                return User.withUsername(username).password(dbUser.getPassword()).authorities("PRODUCT_ADMIN").build();
            }
        };
    }

    @Bean
    public ClientDetailsService clientDetailsService(OauthClientDetailsRepository clientDetailsRepository) {
        return clientId -> {
            OauthClientDetailsDO oauthClientDetailsParam = new OauthClientDetailsDO();
            oauthClientDetailsParam.setClientId(clientId);
            OauthClientDetailsDO clientDetail = clientDetailsRepository.findOne(oauthClientDetailsParam);
            if (Objects.isNull(clientDetail)) {
                throw new ClientRegistrationException("不存在的客户端id");
            }

            BaseClientDetails oauthBaseClientDetail = new BaseClientDetails();
            oauthBaseClientDetail.setClientId(clientDetail.getClientId());
            oauthBaseClientDetail.setClientSecret(clientDetail.getClientSecret());
            String redirectUri = clientDetail.getWebServerRedirectUri();
            if (StringUtils.isNotBlank(redirectUri)) {
                oauthBaseClientDetail.setRegisteredRedirectUri(new HashSet<>(Arrays.asList(clientDetail.getWebServerRedirectUri().split(","))));
            }
            oauthBaseClientDetail.setAuthorizedGrantTypes(Arrays.asList(clientDetail.getAuthorizedGrantTypes().split(",")));
            oauthBaseClientDetail.setScope(Arrays.asList(clientDetail.getScope().split(",")));

            return oauthBaseClientDetail;
        };
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(OauthCodeRepository oauthCodeRepository) {
        return new RandomValueAuthorizationCodeServices() {
            @Override
            protected void store(String code, OAuth2Authentication authentication) {
                OauthCodeDO oauthCodeDO = new OauthCodeDO();
                oauthCodeDO.setCode(code);
                oauthCodeDO.setAuthentication(SerializationUtils.serialize(authentication.getUserAuthentication()));
                oauthCodeRepository.insert(oauthCodeDO);
            }

            @Override
            protected OAuth2Authentication remove(String code) {
                OauthCodeDO oauthCodeDO = new OauthCodeDO();
                oauthCodeDO.setCode(code);
                OauthCodeDO dbCode = oauthCodeRepository.findOne(oauthCodeDO);
                OAuth2Authentication oAuth2Authentication = JSON.parseObject(dbCode.getAuthentication(), OAuth2Authentication.class);

                oauthCodeRepository.delete(oauthCodeDO);

                return oAuth2Authentication;
            }
        };
    }

}