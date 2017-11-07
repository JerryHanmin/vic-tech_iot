package com.vic.iot.common.properties;

import lombok.Data;
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

    @Data
    public static class EurekaProperties {
        /**
         * eureka 的地址(defaultZone) 的配置
         */
        private String uri;
    }

    @Data
    public static class MysqlProperties {
        private String host;
        private String database;
        private String username;
        private String password;
    }

    @Data
    public static class RedisProperties {
        private String host;
    }

    @Data
    public static class MongodbProperties {
        private String uri;
    }
}

