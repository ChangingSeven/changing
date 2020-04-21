package com.changing.bg.framework.configure.security;

import com.changing.bg.model.entity.UserDO;
import com.changing.bg.model.entity.UserPermissionDO;
import com.changing.bg.reposity.UserPermissionReposity;
import com.changing.bg.reposity.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserReposity userReposity;
    @Autowired
    private UserPermissionReposity userPermissionReposity;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            UserDO userParam = new UserDO();
            userParam.setUserName(username);
            UserDO dbUser = userReposity.getUser(userParam);
            if (null == dbUser) {
                throw new IllegalArgumentException("Username : " + username + " not found !");
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
                return new User(dbUser.getUserName(), dbUser.getPassword(), new ArrayList<>());
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/login");
    }

}