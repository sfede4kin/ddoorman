package ru.ddoorman.door.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.client.model.enumeration.EventTypeEnum;
import ru.ddoorman.door.component.DoorSessionComponent;
import ru.ddoorman.door.model.dto.DtoUtil;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);
    private final SimpMessagingTemplate messagingTemplate;
    private final DoorSessionComponent doorSessionComponent;
    private final KafkaProducerService kafkaProducerService;
    private final EventService eventService;

    public KafkaConsumerServiceImpl(SimpMessagingTemplate messagingTemplate, DoorSessionComponent doorSessionComponent,
                                    KafkaProducerService kafkaProducerService, EventService eventService){
        this.messagingTemplate = messagingTemplate;
        this.doorSessionComponent = doorSessionComponent;
        this.kafkaProducerService = kafkaProducerService;
        this.eventService = eventService;
    }

    @KafkaListener(topics = "#{kafkaConfig.consumerTopicName}", groupId = "#{kafkaConfig.consumerGroupId}")
    public void consume(EventDto event){
        log.info("event consumed: {}", event.toString());

        if(doorSessionComponent.containsValue(event.getDoorId())) {
            log.info("door is connected: {}", event.getDoorId());
            messagingTemplate.convertAndSend("/queue/event." + event.getDoorId(), DtoUtil.cloneEventDto(event));
        }else{
            log.info("door is disconnected: {}", event.getDoorId());
            EventDto eventResponse = DtoUtil.getResponseEventDto(event, EventTypeEnum.FAILED);
            kafkaProducerService.sendMessage(eventResponse);
            eventService.save(DtoUtil.cloneEventDtoToEvent(eventResponse));
        }
    }

}
