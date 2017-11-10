package com.vic.iot.netty.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("service.netty.server")
public class NettyServerProperties {
    private Integer tcpPort = 8883;

    private Integer bossCount = 5;

    private Integer workerCount = 5;

    private Boolean keepAlive = true;

    private Integer backlog = 5;

    private String loggerName = "test";
}
