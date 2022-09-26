package ru.ddoorman.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.client.service.EventService;
import ru.ddoorman.client.service.KafkaProducerService;

@Controller
public class EventWebsocketController {
    private static final Logger log = LoggerFactory.getLogger(EventWebsocketController.class);
    private final KafkaProducerService kafkaProducerService;
    private final EventService eventService;
    public EventWebsocketController(KafkaProducerService kafkaProducerService, EventService eventService){
        this.kafkaProducerService = kafkaProducerService;
        this.eventService = eventService;
    }

    @MessageMapping("/event.{accountId}")
    public void sendEvent(@DestinationVariable String accountId, EventDto event){
        log.debug(event.toString());
        kafkaProducerService.sendMessage(event);
        eventService.save(DtoUtil.cloneDtoToEvent(event));
    }
}
