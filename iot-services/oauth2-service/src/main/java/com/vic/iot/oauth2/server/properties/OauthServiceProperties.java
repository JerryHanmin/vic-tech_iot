package com.vic.iot.oauth2.server.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
@Data
public class OauthServiceProperties {

    private OauthProperties oauth = new OauthProperties();
    private UserProperties user = new UserProperties();
    private SystemProperties system = new SystemProperties();
}
