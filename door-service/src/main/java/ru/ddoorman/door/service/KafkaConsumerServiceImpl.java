package ru.ddoorman.door.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.client.model.enumeration.EventTypeEnum;
import ru.ddoorman.door.component.DoorSessionComponent;
import ru.ddoorman.door.model.dto.DtoUtil;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);
    private final DoorSessionComponent doorSessionComponent;
    private final KafkaProducerService kafkaProducerService;
    private final EventMessagingService eventMessagingService;

    public KafkaConsumerServiceImpl(DoorSessionComponent doorSessionComponent,
                                    KafkaProducerService kafkaProducerService,
                                    EventMessagingService eventMessagingService){
        this.doorSessionComponent = doorSessionComponent;
        this.kafkaProducerService = kafkaProducerService;
        this.eventMessagingService = eventMessagingService;
    }

    @KafkaListener(topics = "#{kafkaConfig.consumerTopicName}", groupId = "#{kafkaConfig.consumerGroupId}")
    public void consume(EventDto event){
        log.info("event consumed: {}", event.toString());

        if(doorSessionComponent.containsValue(event.getDoorId())) {
            log.info("door is connected: {}", event.getDoorId());
            eventMessagingService.send(event);
        }else{
            log.info("door is disconnected: {}", event.getDoorId());
            EventDto eventResponse = DtoUtil.getResponseEventDto(event, EventTypeEnum.FAILED);
            kafkaProducerService.sendMessage(eventResponse);
        }
    }
}
