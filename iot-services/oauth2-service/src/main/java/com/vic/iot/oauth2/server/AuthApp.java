package com.vic.iot.oauth2.server;

import com.vic.iot.banner.BuddhaBanner;
import com.vic.iot.oauth2.server.properties.OauthServiceProperties;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.vic.iot"})
@EnableEurekaClient
@EnableConfigurationProperties(OauthServiceProperties.class)
public class AuthApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder().banner(new BuddhaBanner()).bannerMode(Banner.Mode.LOG).sources(AuthApp.class).run(args);
    }
}
