package ru.ddoorman.door.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.door.model.dto.DtoUtil;
import ru.ddoorman.client.model.enumeration.EventTypeEnum;
import ru.ddoorman.door.service.EventService;
import ru.ddoorman.door.service.KafkaConsumerService;
import ru.ddoorman.door.service.KafkaProducerService;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class EventWebsocketController {
    private static final Logger log = LoggerFactory.getLogger(EventWebsocketController.class);
    private final KafkaConsumerService kafkaConsumerService;
    private final KafkaProducerService kafkaProducerService;
    private final EventService eventService;
    private final SimpMessagingTemplate messagingTemplate;
    private final static String DEST_RESP_EVENT = "/topic/response.event.";

    public EventWebsocketController(KafkaConsumerService kafkaConsumerService,
                                    KafkaProducerService kafkaProducerService,
                                    EventService eventService,
                                    SimpMessagingTemplate messagingTemplate) {
        this.kafkaConsumerService = kafkaConsumerService;
        this.kafkaProducerService = kafkaProducerService;
        this.eventService = eventService;
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        log.info("Client with username {} subscribed", event.getUser());
        Set<String> threadSafeUniqueNumbers = ConcurrentHashMap.newKeySet();
    }
    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        log.info("Client with username {} disconnected", event.getUser());
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
