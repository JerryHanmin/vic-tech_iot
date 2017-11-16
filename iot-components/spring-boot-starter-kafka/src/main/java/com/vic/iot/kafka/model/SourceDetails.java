package com.vic.iot.kafka.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SourceDetails {
    private String name;
    private AvroDetails source;
}
