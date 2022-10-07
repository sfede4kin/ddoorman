package ru.ddoorman.extractor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.extractor.model.dto.DtoUtil;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);
    private final EventDatastoreService eventDatastoreService;

    public KafkaConsumerServiceImpl(EventDatastoreService eventDatastoreService) {
        this.eventDatastoreService = eventDatastoreService;
    }

    @KafkaListener(topics = {"#{kafkaConfig.consumerTopicRequestName}", "#{kafkaConfig.consumerTopicResponseName}"},
                    groupId = "#{kafkaConfig.consumerGroupId}")
    public void consume(EventDto event) {
        log.debug("event consumed: {}", event.toString());
        eventDatastoreService.save(DtoUtil.cloneEventDtoToEvent(event));
    }
}
