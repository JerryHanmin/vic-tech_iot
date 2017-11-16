package com.vic.iot.kafka.model;

import avro.shaded.com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

@Data
public class KafkaAvroConfig {
    private Map<String, AvroDetails> avroDetails = Maps.newConcurrentMap();
}
