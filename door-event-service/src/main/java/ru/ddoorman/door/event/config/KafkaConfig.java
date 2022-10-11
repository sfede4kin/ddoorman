package ru.ddoorman.door.event.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value(value = "${spring.kafka.consumer.topic.request.name}")
    private String consumerTopicRequestName;

    @Value(value = "${spring.kafka.consumer.topic.response.name}")
    private String consumerTopicResponseName;
    @Value(value = "${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return new KafkaAdmin(configs);
    }

    public String getConsumerTopicRequestName() {
        return consumerTopicRequestName;
    }

    public String getConsumerTopicResponseName() {
        return consumerTopicResponseName;
    }

    public String getConsumerGroupId() {
        return consumerGroupId;
    }
}
