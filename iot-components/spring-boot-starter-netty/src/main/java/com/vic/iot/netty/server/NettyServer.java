package com.vic.iot.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Slf4j
public class NettyServer {

    private Channel serverChannel;

    @Autowired
    private NettyServerProperties nettyServerProperties;


    public void start(ServerBootstrap serverBootstrap) throws Exception {
        log.info("netty server is starting");
        serverChannel = serverBootstrap
                .bind(nettyServerProperties.getTcpPort())
                .sync().channel()
                .closeFuture()
                .sync()
                .channel();
    }

    @PreDestroy
    public void stop() throws Exception {
        log.info("netty server is stopping");
        serverChannel.close();
        serverChannel.parent().close();
    }
}
