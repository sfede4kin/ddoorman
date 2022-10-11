package ru.ddoorman.door.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.ddoorman.door.model.dto.DtoUtil;
import ru.ddoorman.client.model.dto.EventDto;

@Service
public class EventMessagingServiceImpl implements EventMessagingService {
    private final static Logger log = LoggerFactory.getLogger(EventMessagingServiceImpl.class);
    private final SimpMessagingTemplate messagingTemplate;

    private static final String DEST_QUEUE_EVENT = "/queue/event.";

    public EventMessagingServiceImpl(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void send(EventDto event) {
        try {
            var eventCloned = DtoUtil.cloneEventDto(event);
            var destination = DEST_QUEUE_EVENT + eventCloned.getDoorId();
            messagingTemplate.convertAndSend(destination, eventCloned);
        }catch (Exception e){
            log.error("event send to client error", e);
            throw new MessagingException("event send to client error", e);
        }
    }
}
