package com.vic.iot.netty.server;

import com.vic.iot.netty.server.handler.DefaultAuthHandler;
import com.vic.iot.netty.server.handler.DefaultIdleTimeoutHandler;
import com.vic.iot.netty.server.handler.DefaultServiceHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private static final int READER_IDLE_TIME_SECONDS = 20; //读操作空闲20秒
    private static final int WRITER_IDLE_TIME_SECONDS = 20; //写操作空闲20秒
    private static final int ALL_IDLE_TIME_SECONDS = 40; //读写全部空闲40秒

    @Autowired
    private DefaultAuthHandler authHandler;
    @Autowired
    private DefaultServiceHandler serviceHandler;
    @Autowired
    private DefaultIdleTimeoutHandler idleTimeoutHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("idleStateHandler",
                new IdleStateHandler(READER_IDLE_TIME_SECONDS,
                        WRITER_IDLE_TIME_SECONDS,
                        ALL_IDLE_TIME_SECONDS,
                        TimeUnit.SECONDS))
                .addLast("idleTimeoutHandler", idleTimeoutHandler)
                .addLast("authHandler", authHandler)
                .addLast("serviceHandler", serviceHandler);
    }
}
