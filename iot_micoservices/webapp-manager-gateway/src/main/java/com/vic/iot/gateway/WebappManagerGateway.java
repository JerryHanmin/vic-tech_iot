package com.vic.iot.gateway;

import com.vic.iot.gateway.properties.ServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties(ServiceProperties.class)
@ComponentScan("com.vic.iot.gateway")
public class WebappManagerGateway {
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

    public static void main(String[] args) {
        SpringApplication.run(WebappManagerGateway.class, args);
    }
}
