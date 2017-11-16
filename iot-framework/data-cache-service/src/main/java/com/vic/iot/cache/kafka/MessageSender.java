package com.vic.iot.cache.kafka;

import com.vic.iot.cache.model.AddDeviceCache;
import com.vic.iot.kafka.KafkaProducer;
import com.vic.iot.kafka.model.AvroDetails;
import com.vic.iot.kafka.model.KafkaAvroConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaAvroConfig kafkaAvroConfig;

    public void sendAddDeviceCacheMsg(AddDeviceCache addDeviceCache) {
        AvroDetails avroDetails = kafkaAvroConfig.getAvroDetails().get("device_cache_add");
        kafkaProducer.send(avroDetails.getTopic(), avroDetails.getSchema(), addDeviceCache);
    }
}
