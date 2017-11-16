package com.vic.iot.kafka.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConfigDetails {
    private String name;
    private String[] profiles;
    private String label;
    private String version;
    private String state;
    private SourceDetails[] propertySources;

}
