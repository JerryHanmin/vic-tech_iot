/**
 *
 */
package com.vic.oauth2.server.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class TokenJwtEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String userName = authentication.getUserAuthentication().getName();
        User user = (User) authentication.getUserAuthentication().getPrincipal();// 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
        /** 自定义一些token属性 ***/
        final Map<String, Object> infos = new HashMap<>();
        infos.put("userName", userName);
        infos.put("roles", user.getAuthorities());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infos);
        return accessToken;
    }

}
