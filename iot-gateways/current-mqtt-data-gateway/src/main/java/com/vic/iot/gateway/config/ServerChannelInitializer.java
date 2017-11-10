package com.vic.iot.gateway.config;

import com.vic.iot.gateway.handler.IdleTimeoutHandler;
import com.vic.iot.gateway.handler.MqttAuthHandler;
import com.vic.iot.gateway.handler.MqttServiceHandler;
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
    private MqttAuthHandler mqttAuthHandler;
    @Autowired
    private MqttServiceHandler mqttServiceHandler;
    @Autowired
    private IdleTimeoutHandler idleTimeoutHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("idleStateHandler",
                new IdleStateHandler(READER_IDLE_TIME_SECONDS,
                        WRITER_IDLE_TIME_SECONDS,
                        ALL_IDLE_TIME_SECONDS,
                        TimeUnit.SECONDS))
                .addLast("idleTimeoutHandler", idleTimeoutHandler)
                .addLast("mqttAuthHandler", mqttAuthHandler)
                .addLast("mqttServiceHandler", mqttServiceHandler);
    }
}
