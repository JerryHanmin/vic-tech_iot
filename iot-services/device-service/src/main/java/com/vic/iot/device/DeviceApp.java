package com.vic.iot.device;

import com.vic.iot.common.BuddhaBanner;
import com.vic.iot.device.properties.DeviceServiceProperties;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.vic.iot"})
@EnableEurekaClient
@EnableConfigurationProperties(DeviceServiceProperties.class)
public class DeviceApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder().banner(new BuddhaBanner()).bannerMode(Banner.Mode.LOG).sources(DeviceApp.class).run(args);
    }

}
