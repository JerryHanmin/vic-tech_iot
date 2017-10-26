package com.vic.iot.webappGateway.properties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserProperties {
    private String prefix = "http://user-service/users";
    private String users = "";
    private String register = "/register";
}
