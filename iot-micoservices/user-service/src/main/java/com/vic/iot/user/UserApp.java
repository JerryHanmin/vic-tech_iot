package com.vic.iot.user;

import com.vic.iot.common.BuddhaBanner;
import com.vic.iot.user.properties.UserServiceProperties;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.vic.iot"})
@EnableEurekaClient
@EnableConfigurationProperties(UserServiceProperties.class)
public class UserApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder().banner(new BuddhaBanner()).bannerMode(Banner.Mode.LOG).sources(UserApp.class).run(args);
    }

}
