package com.vic.iot.gateway;

import com.vic.iot.gateway.properties.GatewayServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.vic.iot")
@EnableConfigurationProperties(GatewayServiceProperties.class)
public class WebappManagerGateway {
    public static void main(String[] args) {
        SpringApplication.run(WebappManagerGateway.class, args);
    }
}
