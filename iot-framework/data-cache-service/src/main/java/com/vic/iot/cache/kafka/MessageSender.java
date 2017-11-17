package com.vic.iot.cache.kafka;

import com.vic.iot.cache.model.AddDeviceCache;
import com.vic.iot.kafka.KafkaProducer;
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
        kafkaProducer.send("test", kafkaAvroConfig.getAvroDetails().get("device_cache_add"), addDeviceCache);
    }
}
