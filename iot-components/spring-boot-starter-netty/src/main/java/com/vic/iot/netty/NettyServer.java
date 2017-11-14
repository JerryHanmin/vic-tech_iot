package com.vic.iot.netty;

import com.google.common.collect.Lists;
import com.vic.iot.netty.handler.NettyTransportHandlerRegister;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class NettyServer implements ApplicationContextAware {


    private Channel channel;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private List<ChannelHandler> handlers = Lists.newArrayList();
    private List<ChannelHandler> decoders = Lists.newArrayList();
    private List<ChannelHandler> encoders = Lists.newArrayList();
    private List<GenericFutureListener<Future<? super Void>>> listeners = Lists.newArrayList();

    @Autowired
    private NettyServerProperties nettyServerProperties;


    @PostConstruct
    public void start() throws Exception {
        log.info("netty server is starting");
        bossGroup = new NioEventLoopGroup(nettyServerProperties.getBossGroupThreadCount());
        workerGroup = new NioEventLoopGroup(nettyServerProperties.getWorkGroupThreadCount());


        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();

                        for (ChannelHandler decoder : decoders) {
                            channelPipeline.addLast(decoder);
                        }
                        for (ChannelHandler encoder : encoders) {
                            channelPipeline.addLast(encoder);
                        }
                        for (ChannelHandler handler : handlers) {
                            channelPipeline.addLast(handler);
                        }
                        for (GenericFutureListener<Future<? super Void>> listener : listeners) {
                            socketChannel.closeFuture().addListener(listener);
                        }
                    }
                });

        channel = serverBootstrap.bind(nettyServerProperties.getHost(), nettyServerProperties.getPort()).sync().channel();
    }

    @PreDestroy
    public void stop() throws Exception {
        log.info("destroy server resources");
        if (null == channel) {
            log.error("server channel is null");
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
        bossGroup = null;
        workerGroup = null;
        channel = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, NettyTransportHandlerRegister> codecMap = applicationContext.getBeansOfType(NettyTransportHandlerRegister.class);
        for (String name : codecMap.keySet()) {
            NettyTransportHandlerRegister transportHandlers = codecMap.get(name);

            decoders.addAll(transportHandlers.decoders());
            encoders.addAll(transportHandlers.encoders());
            handlers.addAll(transportHandlers.handlers());
            listeners.addAll(transportHandlers.listeners());
        }
    }
}
