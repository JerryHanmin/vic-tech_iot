package com.vic.iot.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.List;

public interface NettyTransportHandlerRegister {
    List<ChannelHandler> decoders();

    List<ChannelHandler> encoders();

    List<ChannelHandler> handlers();

    List<GenericFutureListener<Future<? super Void>>> listeners();
}
