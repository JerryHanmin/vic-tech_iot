/**
 *
 */
package com.vic.oauth2.server.properties;

import lombok.Data;

@Data
public class LocalProperties {

    private String signingKey;

    private String tokenStore;

}
