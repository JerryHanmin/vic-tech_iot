package com.vic.iot.netty.server;

import com.vic.iot.netty.server.repository.ChannelRepository;
import com.vic.iot.netty.server.repository.HashMapChannelRepository;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class NettyServerConfig {

    @Autowired
    private ServerChannelInitializer serverChannelInitializer;

    @Bean
    @ConditionalOnMissingBean
    public NettyServerProperties nettyServerProperties() {
        return new NettyServerProperties();
    }

    @Bean
    public ServerBootstrap serverBootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(nettyServerProperties().getLoggerName(), LogLevel.DEBUG))
                .childHandler(serverChannelInitializer);

        serverBootstrap.option(ChannelOption.SO_KEEPALIVE, nettyServerProperties().getKeepAlive());
        serverBootstrap.option(ChannelOption.SO_BACKLOG, nettyServerProperties().getBacklog());

        return serverBootstrap;
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(nettyServerProperties().getBossCount());
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(nettyServerProperties().getWorkerCount());
    }

    @Bean
    public InetSocketAddress tcpSocketAddress() {
        return new InetSocketAddress(nettyServerProperties().getTcpPort());
    }

    @Bean
    public ChannelRepository channelRepository() {
        return new HashMapChannelRepository();
    }
}
