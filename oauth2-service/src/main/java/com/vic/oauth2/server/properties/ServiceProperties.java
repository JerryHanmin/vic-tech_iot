package com.vic.oauth2.server.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
@Data
public class ServiceProperties {

    private LocalProperties local = new LocalProperties();
    private UserProperties user = new UserProperties();
}
