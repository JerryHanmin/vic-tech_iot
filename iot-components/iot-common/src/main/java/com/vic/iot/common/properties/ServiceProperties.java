package com.vic.iot.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
@Data
public class ServiceProperties {
    private SwaggerProperties swagger = new SwaggerProperties();
}
