package com.vic.iot.gateway;

import com.vic.iot.banner.BuddhaBanner;
import com.vic.iot.netty.NettyServer;
import io.netty.bootstrap.ServerBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.vic.iot"})
@EnableEurekaClient
public class MqttDataGateway {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .banner(new BuddhaBanner())
                .bannerMode(Banner.Mode.LOG)
                .sources(MqttDataGateway.class)
                .run(args);

    }

}
