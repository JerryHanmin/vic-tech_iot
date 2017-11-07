package com.vic.iot.common.config;

import com.vic.iot.common.properties.ServiceProperties;
import com.vic.iot.swagger.SwaggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class IotConfig {

    @Bean
    @ConditionalOnMissingBean
    public ServiceProperties serviceProperties() {
        return new ServiceProperties();
    }

    /**
     * 注入使用负载均衡的RestTemplate , 否则无法使用服务名访问服务
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @LoadBalanced
    @Bean
    public AsyncRestTemplate asyncRestTemplate(){
        return new AsyncRestTemplate();
    }
}
