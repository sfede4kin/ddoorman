package ru.ddoorman.door.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String BOOTSTRAP_ADDRESS;

    @Value(value = "${spring.kafka.producer.topic.name}")
    private String PRODUCER_TOPIC_NAME;

    @Value(value = "${spring.kafka.partition}")
    private String PARTITION;

    @Value(value = "${spring.kafka.replicas}")
    private String REPLICAS;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_ADDRESS);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(PRODUCER_TOPIC_NAME)
                .partitions(Integer.parseInt(PARTITION))
                .replicas(Short.parseShort(REPLICAS))
                .build();
    }

}
