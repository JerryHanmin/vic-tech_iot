package com.vic.iot.kafka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.apache.avro.Schema;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AvroDetails {
    private String name;
    private String topic;
    private String description;
    private String avsc;
    private Schema schema;
}
