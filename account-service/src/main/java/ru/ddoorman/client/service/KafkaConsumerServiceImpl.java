package ru.ddoorman.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.EventDto;
@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);
    private final EventMessagingService eventMessagingService;
    public KafkaConsumerServiceImpl(EventMessagingService eventMessagingService){
        this.eventMessagingService = eventMessagingService;
    }
    @KafkaListener(topics = "#{kafkaConfig.consumerTopicName}", groupId = "#{kafkaConfig.consumerGroupId}")
    public void consume(EventDto event){
        log.info("event consumed: {}", event.toString());
        eventMessagingService.send(event);
    }
}
