package ru.ddoorman.door.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.EventDto;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    @Override
    @KafkaListener(topics = "requestEventTopic", groupId = "door")
    public void consume(EventDto event){
        log.debug("event consumed: {}", event.toString());
    }

}
