package com.vic.iot.user;

import com.vic.iot.user.properties.UserServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.vic.iot")
@EnableConfigurationProperties(UserServiceProperties.class)
public class UserApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);
    }

}
