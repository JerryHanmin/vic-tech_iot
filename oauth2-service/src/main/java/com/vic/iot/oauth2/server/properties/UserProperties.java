package com.vic.iot.oauth2.server.properties;

import lombok.Data;

@Data
public class UserProperties {
    private String prefix = "http://user-service/users";
    private String findUser = "/by/%s";
}
