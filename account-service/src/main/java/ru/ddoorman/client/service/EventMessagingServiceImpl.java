package ru.ddoorman.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.model.dto.EventDto;

@Service
public class EventMessagingServiceImpl implements EventMessagingService{
    private final static Logger log = LoggerFactory.getLogger(EventMessagingServiceImpl.class);
    private final SimpMessagingTemplate messagingTemplate;

    private static final String DEST_RESP_EVENT = "/queue/response.event.";

    public EventMessagingServiceImpl(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void send(EventDto event) {
        try {
            var eventCloned = DtoUtil.cloneEventDto(event);
            var destination = DEST_RESP_EVENT + eventCloned.getAccountId() + '-' + eventCloned.getAppSessionId();
            messagingTemplate.convertAndSend(destination, eventCloned);
        }catch (Exception e){
            log.error("event send to client error", e);
            throw new MessagingException("event send to client error", e);
        }
    }
}
