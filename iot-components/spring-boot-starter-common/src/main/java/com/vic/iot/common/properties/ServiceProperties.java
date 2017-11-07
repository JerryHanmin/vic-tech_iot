package com.vic.iot.common.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("service")
public class ServiceProperties {
    /**
     * Redis 数据库配置
     */
    private RedisProperties redis = new RedisProperties();
    /**
     * Mysql 数据库配置
     */
    private MysqlProperties mysql = new MysqlProperties();
    /**
     * MongoDB 数据库配置
     */
    private MongodbProperties mongodb = new MongodbProperties();
    /**
     * 服务发现配置
     */
    private EurekaProperties eureka = new EurekaProperties();
    /**
     * 国际化配置
     */
    private I18nProperties i18n = new I18nProperties();

    @Data
    @NoArgsConstructor
    public static class I18nProperties {
        private Boolean enable = true;
    }

    @Data
    @NoArgsConstructor
    public static class EurekaProperties {
        /**
         * eureka 的地址(defaultZone) 的配置
         */
        private String uri;
    }

    @Data
    @NoArgsConstructor
    public static class MysqlProperties {
        private String host;
        private String database;
        private String username;
        private String password;
    }

    @Data
    @NoArgsConstructor
    public static class RedisProperties {
        private String host;
    }

    @Data
    @NoArgsConstructor
    public static class MongodbProperties {
        private String uri;
    }
}

