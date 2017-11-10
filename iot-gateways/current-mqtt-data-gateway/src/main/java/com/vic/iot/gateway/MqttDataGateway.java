package com.vic.iot.gateway;

import com.vic.iot.banner.BuddhaBanner;
import com.vic.iot.gateway.handler.MqttAuthHandler;
import com.vic.iot.netty.server.NettyServer;
import com.vic.iot.netty.server.ServerChannelInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.vic.iot"})
@EnableEurekaClient
public class MqttDataGateway {

    @Autowired
    private MqttAuthHandler mqttAuthHandler;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .banner(new BuddhaBanner())
                .bannerMode(Banner.Mode.LOG)
                .sources(MqttDataGateway.class)
                .run(args);

        NettyServer server = context.getBean(NettyServer.class);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
