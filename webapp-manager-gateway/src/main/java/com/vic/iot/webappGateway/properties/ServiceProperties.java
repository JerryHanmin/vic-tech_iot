package com.vic.iot.webappGateway.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
@Data
public class ServiceProperties {

    private Oauth2Properties oauth2 = new Oauth2Properties();

    private LocalProperties local = new LocalProperties();

    private UserProperties user = new UserProperties();
}
