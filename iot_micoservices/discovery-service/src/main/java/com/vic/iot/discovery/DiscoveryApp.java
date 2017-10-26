package com.vic.iot.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableEurekaClient
public class DiscoveryApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DiscoveryApp.class, args);
    }

}
