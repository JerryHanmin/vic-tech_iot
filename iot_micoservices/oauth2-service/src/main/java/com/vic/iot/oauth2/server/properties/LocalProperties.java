/**
 *
 */
package com.vic.iot.oauth2.server.properties;

import lombok.Data;

@Data
public class LocalProperties {

    private String signingKey;

    private String tokenStore;

}
