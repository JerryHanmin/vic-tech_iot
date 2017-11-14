package com.vic.iot.gateway.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service.mqtt")
@Data
public class MqttServiceProperties {
}
