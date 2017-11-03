/**
 *
 */
package com.vic.iot.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;


    @Autowired
    private UserDetailsService oauth2UserDetailsService;

    @Autowired
    private ClientDetailsService oauth2ClientDetailsService;

    /**
     * 认证及token配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).userDetailsService(oauth2UserDetailsService)
                .tokenStore(tokenStore);
        if (null != jwtAccessTokenConverter) {
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtAccessTokenConverter);
            if (null != jwtTokenEnhancer) {
                enhancers.add(jwtTokenEnhancer);
            }

            enhancerChain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }

    }

    /**
     * 定义令牌端点上的安全约束
     * hasRole([role])	 返回 true 如果当前主体拥有特定角色。
     * hasAnyRole([role1,role2])	 返回 true 如果当前主体拥有任何一个提供的角色 （使用逗号分隔的字符串队列）
     * principal	 允许直接访问主体对象，表示当前用户
     * authentication	 允许直接访问当前 Authentication对象 从SecurityContext中获得
     * permitAll	 一直返回true
     * denyAll	 一直返回false
     * isAnonymous()	 如果用户是一个匿名登录的用户 就会返回 true
     * isRememberMe()	 如果用户是通过remember-me 登录的用户 就会返回 true
     * isAuthenticated()	 如果用户不是匿名用户就会返回true
     * isFullyAuthenticated()	 如果用户不是通过匿名也不是通过remember-me登录的用户时， 就会返回true
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.accessDeniedHandler(new OAuth2AccessDeniedHandler()).tokenKeyAccess("isAuthenticated() || isRememberMe()").checkTokenAccess("isAuthenticated() || isRememberMe()");
    }

    /**
     * 配置客户端信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(oauth2ClientDetailsService);
    }

}
