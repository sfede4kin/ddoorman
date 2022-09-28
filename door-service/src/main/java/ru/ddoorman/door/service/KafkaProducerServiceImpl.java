package ru.ddoorman.door.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.ddoorman.client.model.dto.EventDto;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    public final static Logger log = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    private final KafkaTemplate<String, EventDto> kafkaTemplate;
    @Value(value = "${spring.kafka.producer.topic.name}")
    private String TOPIC_NAME;

    public KafkaProducerServiceImpl(KafkaTemplate<String, EventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EventDto msg) {
        log.info("topic: {}, msg: {}", TOPIC_NAME, msg);
        log.info("topic: {}, msg: {}", TOPIC_NAME, msg);
        ListenableFuture<SendResult<String, EventDto>> future =
                kafkaTemplate.send(new ProducerRecord<String, EventDto>(TOPIC_NAME, msg.getSourceId(), msg));

        future.addCallback(result -> {
            log.info("kafka send ok, offset {}", result.getRecordMetadata().offset());
        }, (KafkaFailureCallback<String, EventDto>) ex -> {
            log.error("kafka send error: {}", ex.getMessage());
            //ProducerRecord<Long, EventDto> failed = ex.getFailedProducerRecord();
        });

    }
}
