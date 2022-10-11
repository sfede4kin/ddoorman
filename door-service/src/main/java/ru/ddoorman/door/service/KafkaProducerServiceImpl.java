package ru.ddoorman.door.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.door.config.KafkaConfig;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    public final static Logger log = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    private final KafkaTemplate<String, EventDto> kafkaTemplate;
    private final KafkaConfig kafkaConfig;

    public KafkaProducerServiceImpl(KafkaTemplate<String, EventDto> kafkaTemplate, KafkaConfig kafkaConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfig = kafkaConfig;
    }

    public void sendMessage(EventDto msg) {
        log.info("topic: {}, msg: {}", kafkaConfig.getProducerTopicName(), msg);
        ListenableFuture<SendResult<String, EventDto>> future =
                kafkaTemplate.send(new ProducerRecord<String, EventDto>(kafkaConfig.getProducerTopicName(), msg.getSourceId(), msg));

        future.addCallback(result -> {
            log.info("kafka send ok, offset {}", result.getRecordMetadata().offset());
        }, (KafkaFailureCallback<String, EventDto>) ex -> {
            log.error("event id kafka send error: {} - {}", ex.getFailedProducerRecord().key(), ex.getMessage());
        });

    }
}
