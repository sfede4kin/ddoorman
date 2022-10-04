package ru.ddoorman.client.config;

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
public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstratServer;

    @Value(value = "${spring.kafka.producer.topic.name}")
    private String producerTopicName;

    @Value(value = "${spring.kafka.partition}")
    private String partition;

    @Value(value = "${spring.kafka.replicas}")
    private String replicas;

    @Value(value = "${spring.kafka.consumer.topic.name}")
    private String consumerTopicName;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstratServer);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(producerTopicName)
                .partitions(Integer.parseInt(partition))
                .replicas(Short.parseShort(replicas))
                .build();
    }

    public String getProducerTopicName() {
        return producerTopicName;
    }

    public String getConsumerTopicName() {
        return consumerTopicName;
    }

    public String getConsumerGroupId() {
        return consumerGroupId;
    }
}
