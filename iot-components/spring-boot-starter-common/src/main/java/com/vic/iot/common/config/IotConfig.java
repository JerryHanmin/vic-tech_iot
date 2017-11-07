package com.vic.iot.common.config;

import com.vic.iot.common.properties.ServiceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class IotConfig {

    /**
     * 注入使用负载均衡的RestTemplate , 否则无法使用服务名访问服务
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 异步RestTemplate , 当需要高并发访问的时候应该用异步的方式提高效率
     */
    @LoadBalanced
    @Bean
    public AsyncRestTemplate asyncRestTemplate(){
        return new AsyncRestTemplate();
    }
}
