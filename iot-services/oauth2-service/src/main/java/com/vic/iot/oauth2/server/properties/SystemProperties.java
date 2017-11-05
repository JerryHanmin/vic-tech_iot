package com.vic.iot.oauth2.server.properties;

import lombok.Data;

@Data
public class SystemProperties {
    private String prefix = "http://system-service/system";
    private String clients = "/clients";
    private String findClientById = "/clients/%s";
}
