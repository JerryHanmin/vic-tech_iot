package com.vic.iot.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;

import java.util.List;

@Data
@ConfigurationProperties("service.kafka")
public class IotKafkaProperties {
    private String bootstrapServers;
    private String clientId;


    private ProducerProperties producer = new ProducerProperties();
    private ConsumerProperties consumer = new ConsumerProperties();
    private ListenerProperties listener = new ListenerProperties();
    private AvroProperties avro = new AvroProperties();

    @Data
    @NoArgsConstructor
    public static class AvroProperties {
        private String configPath = "http://config-service/config/%s-default.json";
        private String[] name = new String[]{};
        private Boolean enable = true;
    }

    @Data
    @NoArgsConstructor
    public static class ListenerProperties {
        /**
         * Listener AckMode; see the spring-kafka documentation.
         */
        private AbstractMessageListenerContainer.AckMode ackMode;

        /**
         * Number of threads to run in the listener containers.
         */
        private Integer concurrency;

        /**
         * Timeout in milliseconds to use when polling the consumer.
         */
        private Long pollTimeout;

        /**
         * Number of records between offset commits when ackMode is "COUNT" or
         * "COUNT_TIME".
         */
        private Integer ackCount;

        /**
         * Time in milliseconds between offset commits when ackMode is "TIME" or
         * "COUNT_TIME".
         */
        private Long ackTime;
    }

    @Data
    @NoArgsConstructor
    public static class ProducerProperties {
        /**
         * Number of acknowledgments the producer requires the leader to have received
         * before considering a request complete.
         */
        private String acks;

        /**
         * Number of records to batch before sending.
         */
        private Integer batchSize;

        /**
         * Comma-delimited list of host:port pairs to use for establishing the initial
         * connection to the Kafka cluster.
         */
        private List<String> bootstrapServers;

        /**
         * Total bytes of memory the producer can use to buffer records waiting to be sent
         * to the server.
         */
        private Long bufferMemory;

        /**
         * Id to pass to the server when making requests; used for server-side logging.
         */
        private String clientId;

        /**
         * Compression type for all data generated by the producer.
         */
        private String compressionType;

        /**
         * Serializer class for keys.
         */
        private Class<?> keySerializer = StringSerializer.class;

        /**
         * Serializer class for values.
         */
        private Class<?> valueSerializer = StringSerializer.class;

        /**
         * When greater than zero, enables retrying of failed sends.
         */
        private Integer retries;
    }

    @Data
    @NoArgsConstructor
    public static class ConsumerProperties {
        /**
         * Frequency in milliseconds that the consumer offsets are auto-committed to Kafka
         * if 'enable.auto.commit' true.
         */
        private Integer autoCommitInterval;

        /**
         * What to do when there is no initial offset in Kafka or if the current offset
         * does not exist any more on the server.
         */
        private String autoOffsetReset;

        /**
         * Comma-delimited list of host:port pairs to use for establishing the initial
         * connection to the Kafka cluster.
         */
        private List<String> bootstrapServers;

        /**
         * Id to pass to the server when making requests; used for server-side logging.
         */
        private String clientId;

        /**
         * If true the consumer's offset will be periodically committed in the background.
         */
        private Boolean enableAutoCommit;

        /**
         * Maximum amount of time in milliseconds the server will block before answering
         * the fetch request if there isn't sufficient data to immediately satisfy the
         * requirement given by "fetch.min.bytes".
         */
        private Integer fetchMaxWait;

        /**
         * Minimum amount of data the server should return for a fetch request in bytes.
         */
        private Integer fetchMinSize;

        /**
         * Unique string that identifies the consumer group this consumer belongs to.
         */
        private String groupId;

        /**
         * Expected time in milliseconds between heartbeats to the consumer coordinator.
         */
        private Integer heartbeatInterval;

        /**
         * Deserializer class for keys.
         */
        private Class<?> keyDeserializer = StringDeserializer.class;

        /**
         * Deserializer class for values.
         */
        private Class<?> valueDeserializer = StringDeserializer.class;

        /**
         * Maximum number of records returned in a single call to poll().
         */
        private Integer maxPollRecords;
    }

}


