package com.vic.iot.cache.kafka;

import com.vic.iot.cache.model.AddDeviceCache;
import com.vic.iot.kafka.avro.AvroMessageUtils;
import com.vic.iot.kafka.model.KafkaAvroConfig;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private KafkaAvroConfig kafkaAvroConfig;

    @KafkaListener(topics = {"device_cache_add"})
    public void processMessage(byte[] content) {
        AddDeviceCache addDeviceCache = AvroMessageUtils.deserializer(kafkaAvroConfig.getAvroDetails().get("device_cache_add").getSchema(), content, AddDeviceCache.class);
        System.out.println(addDeviceCache);
    }
}
