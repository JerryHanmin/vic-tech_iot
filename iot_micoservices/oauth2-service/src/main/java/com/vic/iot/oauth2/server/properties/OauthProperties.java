package com.vic.iot.oauth2.server.properties;

import lombok.Data;

@Data
public class OauthProperties {
    private String signingKey;
    private String tokenStore;
}
