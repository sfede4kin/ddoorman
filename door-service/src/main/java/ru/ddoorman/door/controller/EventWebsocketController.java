package ru.ddoorman.door.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.door.component.DoorSessionComponent;
import ru.ddoorman.door.model.dto.DtoUtil;
import ru.ddoorman.door.service.EventDatastoreService;
import ru.ddoorman.door.service.KafkaProducerService;

import java.util.List;
import java.util.Map;

@Controller
public class EventWebsocketController {
    private static final Logger log = LoggerFactory.getLogger(EventWebsocketController.class);
    private final KafkaProducerService kafkaProducerService;
    private final EventDatastoreService eventDatastoreService;
    private final DoorSessionComponent doorSessionComponent;
    private final static String DEST_EVENT = "/queue/event.";
    private final static String DEST_RESP_EVENT = "/queue/response.event.";

    public EventWebsocketController(KafkaProducerService kafkaProducerService,
                                    EventDatastoreService eventService,
                                    DoorSessionComponent doorSessionComponent) {

        this.kafkaProducerService = kafkaProducerService;
        this.eventDatastoreService = eventService;
        this.doorSessionComponent = doorSessionComponent;
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionConnectEvent event) {
        log.info("SessionConnectEvent headers: {}", event.getMessage().getHeaders());
        Long doorId = getDoorId(event);
        if(doorSessionComponent.containsValue(doorId)){
            log.error("door is already connected: {}", doorId);
        }
    }
    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        Long doorId = getDoorId(event);
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        doorSessionComponent.put(sha.getSessionId(), doorId);
        log.info("Client subscribed, current map: {}", doorSessionComponent.toString());
    }
    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        doorSessionComponent.remove(sha.getSessionId());
        log.info("Client disconnected, current map: {}", doorSessionComponent.toString());
    }

    @MessageMapping("/response.event.{doorId}")
    public void sendEvent(@DestinationVariable String doorId, EventDto event) {
        log.info("response event from door: {}", event.toString());
        try {
            eventDatastoreService.save(DtoUtil.cloneEventDtoToEvent(event));
            kafkaProducerService.sendMessage(event);
        }catch (Exception e){
            log.error("response event from door processing error", e);
            throw new MessagingException("response event from door processing error", e);
        }
    }

    private Long getDoorId(AbstractSubProtocolEvent event){
        var nativeHeaders = (Map<String, List<String>>)event.getMessage().getHeaders().get("nativeHeaders");
        var doorId = Long.parseLong(nativeHeaders.get("doorId").get(0));
        log.info("door id parsed: {}", doorId);
        return doorId;

    }
}
