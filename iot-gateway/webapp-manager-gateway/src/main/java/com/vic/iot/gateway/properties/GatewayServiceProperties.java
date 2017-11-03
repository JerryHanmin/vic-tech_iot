package com.vic.iot.gateway.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
@Data
public class GatewayServiceProperties {

    private Oauth2Properties oauth2 = new Oauth2Properties();

    private UserProperties user = new UserProperties();
}
