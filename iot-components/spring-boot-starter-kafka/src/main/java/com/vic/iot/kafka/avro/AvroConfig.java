package com.vic.iot.kafka.avro;

import com.vic.iot.kafka.IotKafkaProperties;
import com.vic.iot.kafka.model.AvroDetails;
import com.vic.iot.kafka.model.ConfigDetails;
import com.vic.iot.kafka.model.KafkaAvroConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

@Configuration
@ConditionalOnProperty(prefix = "service.kafka.avro", name = "enable", havingValue = "true", matchIfMissing = true)
@Slf4j
public class AvroConfig {

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;
    @Autowired
    private IotKafkaProperties iotKafkaProperties;

    @Bean
    public KafkaAvroConfig kafkaAvroConfig() {
        KafkaAvroConfig kafkaAvroConfig = new KafkaAvroConfig();
        for (String name : iotKafkaProperties.getAvro().getName()) {
            asyncRestTemplate.getForEntity(String.format(iotKafkaProperties.getAvro().getConfigPath(), name), String.class).addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
                @Override
                public void onSuccess(ResponseEntity<String> response) {
                    String schema = response.getBody();
                    log.info("get avro schema : " + schema);
                    kafkaAvroConfig.getAvroDetails().put(name, new Schema.Parser().parse(schema));
                }

                @Override
                public void onFailure(Throwable t) {
                    log.error("get avro [{0}] error , error is [{1}]", name, t.getMessage(), t);
                }
            });
        }

        return kafkaAvroConfig;
    }
}
