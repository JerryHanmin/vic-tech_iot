package com.vic.iot.kafka.model;

import avro.shaded.com.google.common.collect.Maps;
import lombok.Data;
import org.apache.avro.Schema;

import java.util.Map;

@Data
public class KafkaAvroConfig {
    private Map<String, Schema> avroDetails = Maps.newConcurrentMap();
}
