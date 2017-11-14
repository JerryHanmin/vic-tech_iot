package com.vic.iot.netty;

import com.vic.iot.netty.repository.ChannelRepository;
import com.vic.iot.netty.repository.HashMapChannelRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(NettyServerProperties.class)
public class NettyServerConfig {

    @Bean
    public ChannelRepository channelRepository() {
        return new HashMapChannelRepository();
    }
}
