package com.vic.iot.discovery;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableEurekaClient
public class DiscoveryApp {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder().banner(new BuddhaBanner()).bannerMode(Banner.Mode.LOG).sources(DiscoveryApp.class).run(args);
    }

}
