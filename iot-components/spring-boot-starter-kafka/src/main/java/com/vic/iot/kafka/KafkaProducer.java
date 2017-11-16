package com.vic.iot.kafka;

import com.vic.iot.kafka.avro.AvroMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    public void send(String topic, Schema schema, Object message) {
        try {
            kafkaTemplate.send(topic, AvroMessageUtils.serializer(schema, message));
        } catch (Exception e) {
            log.error("send kafka message error , error is : " + e.getMessage(), e);
        }
    }
}
