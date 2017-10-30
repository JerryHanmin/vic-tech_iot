package com.vic.iot.device;

import com.vic.iot.device.properties.DeviceServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.vic.iot")
@EnableConfigurationProperties(DeviceServiceProperties.class)
public class DeviceApp {
    public static void main(String[] args) {
        SpringApplication.run(DeviceApp.class, args);
    }

}
