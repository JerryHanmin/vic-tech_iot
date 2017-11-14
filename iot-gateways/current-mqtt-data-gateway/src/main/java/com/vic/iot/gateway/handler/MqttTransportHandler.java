package com.vic.iot.gateway.handler;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageIdVariableHeader;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import io.netty.handler.codec.mqtt.MqttUnsubscribeMessage;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import static io.netty.handler.codec.mqtt.MqttConnectReturnCode.CONNECTION_ACCEPTED;
import static io.netty.handler.codec.mqtt.MqttConnectReturnCode.CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD;
import static io.netty.handler.codec.mqtt.MqttMessageType.CONNACK;
import static io.netty.handler.codec.mqtt.MqttMessageType.PINGRESP;
import static io.netty.handler.codec.mqtt.MqttMessageType.PUBACK;
import static io.netty.handler.codec.mqtt.MqttQoS.AT_LEAST_ONCE;
import static io.netty.handler.codec.mqtt.MqttQoS.AT_MOST_ONCE;

/**
 * 连接认证Handler
 */
@ChannelHandler.Sharable
@Slf4j
public class MqttTransportHandler extends ChannelInboundHandlerAdapter implements GenericFutureListener<Future<? super Void>> {

    private volatile boolean connected;
    private final String sessionId = "";

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channel resistered");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("matt transport handler is working , msg is : " + msg);

        if (msg instanceof MqttMessage) {
            MqttMessage message = (MqttMessage) msg;
            switch (message.fixedHeader().messageType()) {
                case CONNECT:
                    processConnect(ctx, (MqttConnectMessage) msg);
                    break;
                case PUBLISH:
                    processPublish(ctx, (MqttPublishMessage) msg);
                    break;
                case SUBSCRIBE:
                    processSubscribe(ctx, (MqttSubscribeMessage) msg);
                    break;
                case UNSUBSCRIBE:
                    processUnsubscribe(ctx, (MqttUnsubscribeMessage) msg);
                    break;
                case PINGREQ:
                    if (checkConnected(ctx)) {
                        ctx.writeAndFlush(new MqttMessage(new MqttFixedHeader(PINGRESP, false, AT_MOST_ONCE, false, 0)));
                    }
                    break;
                case DISCONNECT:
                    if (checkConnected(ctx)) {
                        processDisconnect(ctx);
                    }
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void operationComplete(Future<? super Void> future) throws Exception {

    }

    private void processConnect(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        log.info("process connect");
        processAuthTokenConnect(ctx, msg);
    }

    private void processPublish(ChannelHandlerContext ctx, MqttPublishMessage msg) {
        log.info("processPublish");
        if (!checkConnected(ctx)) {
            return;
        }
        String topicName = msg.variableHeader().topicName();
        int msgId = msg.variableHeader().packetId();
        createMqttPubAckMsg(msgId);
    }

    private void processSubscribe(ChannelHandlerContext ctx, MqttSubscribeMessage msg) {
        log.info("processSubscribe");
    }

    private void processUnsubscribe(ChannelHandlerContext ctx, MqttUnsubscribeMessage msg) {
        log.info("processUnsubscribe");
    }

    private void processDisconnect(ChannelHandlerContext ctx) {
        log.info("processDisconnect");
        ctx.close();
    }

    private boolean checkConnected(ChannelHandlerContext ctx) {
        log.info("check connected");
        if (connected) {
            return true;
        } else {
            log.info("[{}] Closing current session due to invalid msg order [{}][{}]", sessionId);
            ctx.close();
            return false;
        }
    }

    private void processAuthTokenConnect(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        String userName = msg.payload().userName();
        if (StringUtils.isEmpty(userName)) {
            ctx.writeAndFlush(createMqttConnAckMsg(CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD));
            ctx.close();
        } else {
            ctx.writeAndFlush(createMqttConnAckMsg(CONNECTION_ACCEPTED));
            connected = true;
        }
    }

    private MqttConnAckMessage createMqttConnAckMsg(MqttConnectReturnCode returnCode) {
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(CONNACK, false, AT_MOST_ONCE, false, 0);
        MqttConnAckVariableHeader mqttConnAckVariableHeader =
                new MqttConnAckVariableHeader(returnCode, true);
        return new MqttConnAckMessage(mqttFixedHeader, mqttConnAckVariableHeader);
    }

    public static MqttPubAckMessage createMqttPubAckMsg(int requestId) {
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(PUBACK, false, AT_LEAST_ONCE, false, 0);
        MqttMessageIdVariableHeader mqttMsgIdVariableHeader =
                MqttMessageIdVariableHeader.from(requestId);
        return new MqttPubAckMessage(mqttFixedHeader, mqttMsgIdVariableHeader);
    }

}
