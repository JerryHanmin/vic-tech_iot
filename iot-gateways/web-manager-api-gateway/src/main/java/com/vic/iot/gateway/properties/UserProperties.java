package com.vic.iot.gateway.properties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserProperties {
    private String prefix = "http://user-service";
    private String users = "";
    private String register = "/users/register";
}
