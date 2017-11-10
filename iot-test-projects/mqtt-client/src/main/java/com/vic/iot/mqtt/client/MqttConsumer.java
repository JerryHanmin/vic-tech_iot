package com.vic.iot.mqtt.client;

import com.vic.iot.mqtt.handler.DeviceMessageHandler;
import com.vic.iot.mqtt.model.DevicePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.mapping.support.JsonHeaders;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

@Configuration
public class MqttConsumer {

    private static final int COMPLETION_TIMEOUT = 5000;

    @Autowired
    private DeviceMessageHandler deviceMessageHandler;
    @Autowired
    private MqttPahoClientFactory mqttClientFactory;

    @Bean
    public IntegrationFlow mqttInFlow() {
        return IntegrationFlows.from(mqttInbound())
                // log the incoming message
                .wireTap(sf -> sf.handle(logger()))
                // add a json type needed for converting to pojo
                .enrichHeaders(e -> e.header(JsonHeaders.TYPE_ID, DevicePayload.class.getName()))
                // transform the string payload into a pojo (hint provided by type id). Keep the headers this way
                .transform(Transformers.fromJson())
                .handle(deviceMessageHandler)
                .get();
    }

    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("SampleConsumer",
                mqttClientFactory, "SampleTopic");
        adapter.setCompletionTimeout(COMPLETION_TIMEOUT);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }

    private LoggingHandler logger() {
        LoggingHandler loggingHandler = new LoggingHandler("INFO");
        loggingHandler.setLoggerName("sample_in");
        return loggingHandler;
    }
}
