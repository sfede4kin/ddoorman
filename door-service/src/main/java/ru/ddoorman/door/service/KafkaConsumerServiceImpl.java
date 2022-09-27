package ru.ddoorman.door.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.door.model.dto.DtoUtil;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);
    @Value(value = "${spring.kafka.consumer.topic.name}")
    private String TOPIC_NAME;
    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumerServiceImpl(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "requestEventTopic", groupId = "door")
    public void consume(EventDto event){
        log.debug("event consumed: {}", event.toString());
        messagingTemplate.convertAndSend("/topic/event." + event.getDoorId(), DtoUtil.cloneEventDto(event));
    }

}
