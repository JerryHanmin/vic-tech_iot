package com.vic.iot.gateway;

import com.vic.iot.banner.BuddhaBanner;
import com.vic.iot.netty.server.NettyServer;
import io.netty.bootstrap.ServerBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

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

    @Autowired
    private ServerBootstrap serverBootstrap;
    @Autowired
    private NettyServer nettyServer;

    @Autowired
    private void startServer(){
        try {
            nettyServer.start(serverBootstrap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
