package com.vic.iot.mqtt;

import com.vic.iot.banner.BuddhaBanner;
import com.vic.iot.mqtt.client.MqttClientUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.vic.iot"})
@EnableEurekaClient
public class MqttClientApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .banner(new BuddhaBanner())
                .bannerMode(Banner.Mode.LOG)
                .sources(MqttClientApp.class)
                .web(false)
                .run(args);


        MqttClientUtils client = null;
        try {
            client = new MqttClientUtils();
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
