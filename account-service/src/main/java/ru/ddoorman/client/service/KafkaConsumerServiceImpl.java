package ru.ddoorman.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.client.model.enumeration.EventTypeEnum;
import ru.ddoorman.client.model.dto.DtoUtil;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);
    @Value(value = "${spring.kafka.consumer.topic.name}")
    private String TOPIC_NAME;
    private final SimpMessagingTemplate messagingTemplate;
    private final KafkaProducerService kafkaProducerService;
    private final EventService eventService;

    public KafkaConsumerServiceImpl(SimpMessagingTemplate messagingTemplate,
                                    KafkaProducerService kafkaProducerService, EventService eventService){
        this.messagingTemplate = messagingTemplate;
        this.kafkaProducerService = kafkaProducerService;
        this.eventService = eventService;
    }

    @KafkaListener(topics = "responseEventTopic", groupId = "app")
    public void consume(EventDto event){
        log.info("event consumed: {}", event.toString());

        //TODO account session is needed - extend event dto!

        messagingTemplate.convertAndSend("/queue/response.event." + event.getAccountId(), DtoUtil.cloneEventDto(event));

/*        if(doorSessionComponent.containsValue(event.getDoorId())) {
            log.info("door is connected: {}", event.getDoorId());
            messagingTemplate.convertAndSend("/queue/response.event." + event.getDoorId(), DtoUtil.cloneEventDto(event));
        }else{
            log.info("door is disconnected: {}", event.getDoorId());
            EventDto eventResponse = DtoUtil.getResponseEventDto(event, EventTypeEnum.FAILED);
            kafkaProducerService.sendMessage(eventResponse);
            eventService.save(DtoUtil.cloneEventDtoToEvent(eventResponse));
        }*/
    }

}
