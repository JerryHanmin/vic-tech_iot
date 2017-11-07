package com.vic.iot.oauth2.server.config;

import com.vic.iot.oauth2.server.properties.OauthServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

public class TokenStoreConfig {

    @Configuration
    @ConditionalOnProperty(prefix = "service.oauth", name = "tokenStore", havingValue = "redis", matchIfMissing = true)
    public static class RedisStoreConfig {

        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        @Bean()
        public TokenStore redisTokenStore() {
            return new RedisTokenStore(redisConnectionFactory);
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "service.oauth", name = "tokenStore", havingValue = "jwt")
    public static class JwtConfig {

        @Autowired
        private OauthServiceProperties oauthServiceProperties;

        @Bean()
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(oauthServiceProperties.getOauth().getSigningKey());
            return converter;
        }

        @Bean
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer jwtTokenEnhancer() {
            return new TokenJwtEnhancer();
        }

    }
}
