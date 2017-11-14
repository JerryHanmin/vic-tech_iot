package com.vic.iot.gateway.handler;

import com.google.common.collect.Lists;
import com.vic.iot.netty.handler.NettyTransportHandlerRegister;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MqttTransportHandlerRegister implements NettyTransportHandlerRegister {
    private static final int MAX_PAYLOAD_SIZE = 64 * 1024 * 1024;

    private MqttTransportHandler mqttTransportHandler = new MqttTransportHandler();
    private IdleTimeoutHandler idleTimeoutHandler = new IdleTimeoutHandler();

    @Override
    public List<ChannelHandler> decoders() {
        return Lists.newArrayList(new MqttDecoder(MAX_PAYLOAD_SIZE));
    }

    @Override
    public List<ChannelHandler> encoders() {
        return Lists.newArrayList(MqttEncoder.INSTANCE);
    }

    @Override
    public List<ChannelHandler> handlers() {
        return Lists.newArrayList(idleTimeoutHandler, mqttTransportHandler);
    }

    @Override
    public List<GenericFutureListener<Future<? super Void>>> listeners() {
        return Lists.newArrayList(mqttTransportHandler);
    }
}
