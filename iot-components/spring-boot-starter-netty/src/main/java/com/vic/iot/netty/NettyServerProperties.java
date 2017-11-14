package com.vic.iot.netty;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("service.netty.server")
public class NettyServerProperties {
    private String host;
    private Integer port;
    private Integer bossGroupThreadCount;
    private Integer workGroupThreadCount;
}
