package com.vic.apiGateway.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
@Data
public class ServiceProperties {

    private Oauth2 oauth2 = new Oauth2();

    private Local local = new Local();

    private User user = new User();
}
