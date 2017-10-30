package com.vic.iot.user.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service")
@Data
public class UserServiceProperties {
    private TestProp testProp = new TestProp();
}
