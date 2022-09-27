package ru.ddoorman.door.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.ddoorman.door.model.dto.DtoUtil;
import ru.ddoorman.door.model.enumeration.EventTypeEnum;
import ru.ddoorman.door.service.EventService;
import ru.ddoorman.door.service.KafkaConsumerService;
import ru.ddoorman.door.service.KafkaProducerService;

@Controller
public class EventWebsocketController {
    private static final Logger log = LoggerFactory.getLogger(EventWebsocketController.class);
    private final KafkaConsumerService kafkaConsumerService;
    private final KafkaProducerService kafkaProducerService;
    private final EventService eventService;
    private final SimpMessagingTemplate messagingTemplate;
    private final static String DEST_RESP_EVENT = "/topic/response.event.";

    public EventWebsocketController(KafkaConsumerService kafkaConsumerService, KafkaProducerService kafkaProducerService, EventService eventService,
                                    SimpMessagingTemplate messagingTemplate) {
        this.kafkaConsumerService = kafkaConsumerService;
        this.kafkaProducerService = kafkaProducerService;
        this.eventService = eventService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/event.{doorId}")
    public void sendEvent(@DestinationVariable String doorId, EventDto event) {
        log.debug(event.toString());
        EventDto eventResponse = DtoUtil.getResponseEventDto(event, event.getType());
        try {
            kafkaProducerService.sendMessage(event);
            eventService.save(DtoUtil.cloneEventDtoToEvent(event)); //TODO async callback after success send to kafka ?
        } catch (Exception e) {
            log.error("event processing error", e);
            eventResponse = DtoUtil.getResponseEventDto(event, EventTypeEnum.FAILED);
        } finally {
            log.debug("send response event: {}", eventResponse.toString());
            messagingTemplate.convertAndSend(DEST_RESP_EVENT + doorId, eventResponse);
        }
    }
}
