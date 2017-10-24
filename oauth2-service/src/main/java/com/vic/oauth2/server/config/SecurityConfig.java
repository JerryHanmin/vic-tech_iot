package com.vic.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService oauth2UserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(oauth2UserDetailsService);
//                .passwordEncoder(new Md5PasswordEncoder());     //密码MD5加密
    }

}
