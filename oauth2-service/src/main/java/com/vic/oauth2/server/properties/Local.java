/**
 *
 */
package com.vic.oauth2.server.properties;

import lombok.Data;

@Data
public class Local {

    private String signingKey;

    private String tokenStore;

}
