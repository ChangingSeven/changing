package com.changing.bg.framework.configure.security;

import com.changing.bg.model.entity.oauth.OauthAccessTokenDO;
import com.changing.bg.model.entity.oauth.OauthRefreshTokenDO;
import com.changing.bg.reposity.oauth.OauthAccessTokenRepository;
import com.changing.bg.reposity.oauth.OauthRefreshTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
public class SecurityTokenStore implements TokenStore {

    @Autowired
    private OauthAccessTokenRepository oauthAccessTokenRepository;
    @Autowired
    private OauthRefreshTokenRepository oauthRefreshTokenRepository;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken oAuth2AccessToken) {
        OAuth2Authentication auth2Authentication = null;

        try {
            OauthAccessTokenDO accessTokenQueryParam = new OauthAccessTokenDO();
            accessTokenQueryParam.setTokenId(oAuth2AccessToken.getValue());
            OauthAccessTokenDO dbAccessToken = oauthAccessTokenRepository.findOne(accessTokenQueryParam);
            auth2Authentication = SerializationUtils.deserialize(dbAccessToken.getAuthentication());
        } catch (EmptyResultDataAccessException e) {
            log.error("未找到会话: " + oAuth2AccessToken, e);
        }

        log.info("readAuthentication 方法执行成功...");
        return auth2Authentication;
    }

    @Override
    public OAuth2Authentication readAuthentication(String tokenId) {
        OAuth2Authentication auth2Authentication = null;

        try {
            OauthAccessTokenDO accessTokenQueryParam = new OauthAccessTokenDO();
            accessTokenQueryParam.setTokenId(tokenId);
            OauthAccessTokenDO dbAccessToken = oauthAccessTokenRepository.findOne(accessTokenQueryParam);

            auth2Authentication = SerializationUtils.deserialize(dbAccessToken.getAuthentication());
        } catch (EmptyResultDataAccessException e) {
            log.error("未查询到会话Token", e);
        }

        log.info("readAuthentication(token id) 方法执行成功...");
        return auth2Authentication;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        OauthAccessTokenDO accessTokenQueryParam = new OauthAccessTokenDO();
        accessTokenQueryParam.setTokenId(oAuth2AccessToken.getValue());
        OauthAccessTokenDO dbAccessToken = oauthAccessTokenRepository.findOne(accessTokenQueryParam);
        if (Objects.nonNull(dbAccessToken)) {
            log.info(oAuth2AccessToken.getValue() + "为有效token，无需再次存储");
            return;
        }

        String refreshToken = Objects.isNull(oAuth2AccessToken.getRefreshToken())
                ? null : oAuth2AccessToken.getRefreshToken().getValue();
        OauthAccessTokenDO accessTokenSaveParam = new OauthAccessTokenDO();
        accessTokenSaveParam.setTokenId(oAuth2AccessToken.getValue());
        accessTokenSaveParam.setToken(SerializationUtils.serialize((Serializable) oAuth2AccessToken));
        accessTokenSaveParam.setAuthenticationId(authenticationKeyGenerator.extractKey(oAuth2Authentication));
        accessTokenSaveParam.setAuthentication(SerializationUtils.serialize(oAuth2Authentication));
        accessTokenSaveParam.setRefreshToken(refreshToken);
        accessTokenSaveParam.setClientId(oAuth2Authentication.getOAuth2Request().getClientId());

        User user = (User) oAuth2Authentication.getUserAuthentication().getPrincipal();
        accessTokenSaveParam.setUserName(user.getUsername());
        oauthAccessTokenRepository.save(accessTokenSaveParam);

        log.info("storeAccessToken 方法执行成功...");
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenId) {
        OAuth2AccessToken accessToken = null;

        try {
            OauthAccessTokenDO accessTokenQueryParam = new OauthAccessTokenDO();
            accessTokenQueryParam.setTokenId(tokenId);
            OauthAccessTokenDO dbAccessToken = oauthAccessTokenRepository.findOne(accessTokenQueryParam);

            accessToken = SerializationUtils.deserialize(dbAccessToken.getToken());
        } catch (EmptyResultDataAccessException e) {
            log.error("未查询到会话Token", e);
        }

        log.info("readAccessToken 方法执行成功...");
        return accessToken;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken oAuth2AccessToken) {
        OauthAccessTokenDO accessTokenDeleteParam = new OauthAccessTokenDO();
        accessTokenDeleteParam.setTokenId(oAuth2AccessToken.getValue());
        oauthAccessTokenRepository.delete(accessTokenDeleteParam);
        log.info("removeAccessToken 方法执行成功...");
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication oAuth2Authentication) {
        OauthRefreshTokenDO oauthRefreshTokenSaveParam = new OauthRefreshTokenDO();
        oauthRefreshTokenSaveParam.setTokenId(oAuth2RefreshToken.getValue());
        oauthRefreshTokenSaveParam.setToken(SerializationUtils.serialize((Serializable) oAuth2RefreshToken));
        oauthRefreshTokenSaveParam.setAuthentication(SerializationUtils.serialize(oAuth2Authentication));
        oauthRefreshTokenRepository.save(oauthRefreshTokenSaveParam);
        log.info("storeRefreshToken 方法执行成功...");
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        OAuth2RefreshToken refreshToken = null;

        try {
            OauthRefreshTokenDO oauthRefreshTokenQueryParam = new OauthRefreshTokenDO();
            oauthRefreshTokenQueryParam.setTokenId(tokenValue);
            OauthRefreshTokenDO dbRefreshToken = oauthRefreshTokenRepository.findOne(oauthRefreshTokenQueryParam);

            refreshToken = SerializationUtils.deserialize(dbRefreshToken.getToken());
        } catch (EmptyResultDataAccessException e) {
            log.error("获取再刷新 token 失败" + tokenValue, e);
        }

        log.info("readRefreshToken(token id) 方法执行成功...");
        return refreshToken;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        return null;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        OauthRefreshTokenDO oauthRefreshTokenDeleteParam = new OauthRefreshTokenDO();
        oauthRefreshTokenDeleteParam.setTokenId(oAuth2RefreshToken.getValue());
        oauthRefreshTokenRepository.delete(oauthRefreshTokenDeleteParam);
        log.info("removeRefreshToken 方法执行成功...");
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        OauthAccessTokenDO accessTokenDeleteParam = new OauthAccessTokenDO();
        accessTokenDeleteParam.setTokenId(oAuth2RefreshToken.getValue());
        oauthAccessTokenRepository.delete(accessTokenDeleteParam);

        log.info("removeAccessTokenUsingRefreshToken 方法执行成功...");
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication oAuth2Authentication) {
        OAuth2AccessToken accessToken = null;

        try {
            String auth = authenticationKeyGenerator.extractKey(oAuth2Authentication);
            OauthAccessTokenDO accessTokenQueryParam = new OauthAccessTokenDO();
            accessTokenQueryParam.setAuthenticationId(auth);
            OauthAccessTokenDO at = oauthAccessTokenRepository.findOne(accessTokenQueryParam);
            if (null == at) {
                return null;
            } else {
                accessToken = SerializationUtils.deserialize(at.getToken());
            }

        } catch (EmptyResultDataAccessException e) {
            log.error("根据会话获取token失败： " + oAuth2Authentication, e);
        }

        log.info("getAccessToken(oAuth2Authentication) 方法执行成功...");
        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        List<OAuth2AccessToken> tokenList = new ArrayList<>();

        OauthAccessTokenDO accessTokenQueryParam = new OauthAccessTokenDO();
        accessTokenQueryParam.setClientId(clientId);
        accessTokenQueryParam.setUserName(userName);
        List<OauthAccessTokenDO> oauthAccessTokenList = oauthAccessTokenRepository.list(accessTokenQueryParam);
        if (Objects.nonNull(oauthAccessTokenList) && !oauthAccessTokenList.isEmpty()) {
            for (OauthAccessTokenDO accessTokenDO : oauthAccessTokenList) {
                tokenList.add(SerializationUtils.deserialize(accessTokenDO.getToken()));
            }
        }

        return tokenList;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<OAuth2AccessToken> tokenList = new ArrayList<>();

        List<OauthAccessTokenDO> oauthAccessTokenList = oauthAccessTokenRepository.listByClientId(clientId);
        if (Objects.nonNull(oauthAccessTokenList) && !oauthAccessTokenList.isEmpty()) {
            for (OauthAccessTokenDO accessTokenDO : oauthAccessTokenList) {
                tokenList.add(SerializationUtils.deserialize(accessTokenDO.getToken()));
            }
        }

        return tokenList;
    }
}