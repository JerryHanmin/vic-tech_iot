package com.vic.iot.webappGateway.config;

import com.vic.iot.webappGateway.properties.ServiceProperties;
import com.vic.iot.webappGateway.security.MyRemoteTokenServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    private ServiceProperties serviceProperties;

    @Autowired
    private MyRemoteTokenServices myRemoteTokenServices;

    @Autowired
    protected RestTemplate restTemplate;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //一个账号只能登陆一次
        http.sessionManagement().maximumSessions(1);
        //swagger 文档的api不验证
        http.authorizeRequests().antMatchers("/assets/**", "/v2/api-docs/**", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/oauth/**").permitAll();
        //登陆api不验证
        http.authorizeRequests().antMatchers("/login").permitAll();
        //其余所有api都需要Oauth2验证
        http.authorizeRequests().anyRequest().authenticated();

        //异常捕捉
        http.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(serviceProperties.getOauth2().getResourceId());
        resources.tokenServices(myRemoteTokenServices);
    }

}
