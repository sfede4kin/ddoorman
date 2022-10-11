package ru.ddoorman.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.client.model.enumeration.EventTypeEnum;
import ru.ddoorman.client.service.EventMessagingService;
import ru.ddoorman.client.service.KafkaProducerService;

@Controller
public class EventWebsocketController {
    private static final Logger log = LoggerFactory.getLogger(EventWebsocketController.class);
    private final KafkaProducerService kafkaProducerService;
    private final EventMessagingService eventMessagingService;

    public EventWebsocketController(KafkaProducerService kafkaProducerService, EventMessagingService eventMessagingService) {
        this.kafkaProducerService = kafkaProducerService;
        this.eventMessagingService = eventMessagingService;
    }

    @MessageMapping("/event.{accountId}")
    public void sendEvent(EventDto event) {
        log.info("event from account: {}", event.toString());
        EventDto eventResponse = DtoUtil.createReferenceEventDto(event, event.getType());
        try {
            kafkaProducerService.sendMessage(event);
        } catch (Exception e) {
            log.error("event processing error", e);
            eventResponse = DtoUtil.createReferenceEventDto(event, EventTypeEnum.FAILED);
            throw new MessagingException("response event from door processing error", e);
        } finally {
            log.info("send response event: {}", eventResponse.toString());
            eventMessagingService.send(eventResponse);
        }
    }
}
