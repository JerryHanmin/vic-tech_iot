package com.vic.iot.kafka;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableKafka
@EnableConfigurationProperties(IotKafkaProperties.class)
public class KafkaConfig {

    @LoadBalanced
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @LoadBalanced
    @Bean
    @ConditionalOnMissingBean
    public AsyncRestTemplate asyncRestTemplate(){
        return new AsyncRestTemplate();
    }
}
