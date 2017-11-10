package com.vic.iot.gateway.config;

import io.netty.bootstrap.ServerBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttServerConfig {
    @Autowired
    private ServerBootstrap serverBootstrap;

    
}
