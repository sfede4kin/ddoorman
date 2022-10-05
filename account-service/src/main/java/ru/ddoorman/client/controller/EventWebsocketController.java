package ru.ddoorman.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.client.model.enumeration.EventTypeEnum;
import ru.ddoorman.client.service.EventDatastoreService;
import ru.ddoorman.client.service.EventMessagingService;
import ru.ddoorman.client.service.KafkaProducerService;

@Controller
public class EventWebsocketController {
    private static final Logger log = LoggerFactory.getLogger(EventWebsocketController.class);
    private final KafkaProducerService kafkaProducerService;
    private final EventDatastoreService eventDatastoreService;
    private final EventMessagingService eventMessagingService;

    public EventWebsocketController(KafkaProducerService kafkaProducerService, EventDatastoreService eventDatastoreService,
                                    EventMessagingService eventMessagingService) {
        this.kafkaProducerService = kafkaProducerService;
        this.eventDatastoreService = eventDatastoreService;
        this.eventMessagingService = eventMessagingService;
    }

    @MessageMapping("/event.{accountId}")
    public void sendEvent(EventDto event) {
        log.info("event from account: {}", event.toString());
        EventDto eventResponse = DtoUtil.getResponseEventDto(event, event.getType());
        try {
            //TODO async callback after success send to kafka ?
            //https://docs.spring.io/spring-kafka/reference/html/#ex-jdbc-sync
            eventDatastoreService.save(DtoUtil.cloneEventDtoToEvent(event));
            kafkaProducerService.sendMessage(event);
        } catch (Exception e) {
            log.error("event processing error", e);
            eventResponse = DtoUtil.getResponseEventDto(event, EventTypeEnum.FAILED);
            eventDatastoreService.save(DtoUtil.cloneEventDtoToEvent(eventResponse));
        } finally {
            log.info("send response event: {}", eventResponse.toString());
            eventMessagingService.send(eventResponse);
        }
    }
}
