package com.vic.iot.netty.server;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(NettyServerProperties.class)
public class NettyServerConfig {
}
