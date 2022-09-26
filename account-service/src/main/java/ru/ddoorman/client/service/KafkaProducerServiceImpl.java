package ru.ddoorman.client.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    public final static Logger log = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    @Value(value = "${spring.kafka.topic.name}")
    private String TOPIC_NAME;

    public KafkaProducerServiceImpl(KafkaTemplate<Integer, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        log.info("topic: {}, msg: {}", TOPIC_NAME, msg);
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(TOPIC_NAME, msg);

        future.addCallback(result -> {
            log.info("kafka send ok");
        }, (KafkaFailureCallback<Integer, String>) ex -> {
            log.error("kafka send error: {}", ex.getMessage());
            ProducerRecord<Integer, String> failed = ex.getFailedProducerRecord();
        });

    }
}
