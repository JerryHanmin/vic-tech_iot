package com.vic.iot.oauth2.server;

import com.vic.iot.oauth2.server.properties.OauthServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@EnableEurekaClient
@EnableConfigurationProperties(OauthServiceProperties.class)
@ComponentScan("com.vic.iot")
public class AuthApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }
}
