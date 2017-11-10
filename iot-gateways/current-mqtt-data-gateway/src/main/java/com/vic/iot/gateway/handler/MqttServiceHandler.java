package com.vic.iot.gateway.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 业务逻辑handler
 */
@Component
@ChannelHandler.Sharable
@Slf4j
@Qualifier("serviceHandler")
public class MqttServiceHandler extends ChannelInboundHandlerAdapter {
    private final AttributeKey<String> clientInfo = AttributeKey.valueOf("clientInfo");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        log.info("logic handler is working");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
    }
}
