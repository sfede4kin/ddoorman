package ru.ddoorman.client.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.model.dto.EventDto;

@Service
public class EventMessagingServiceImpl implements EventMessagingService{
    private final SimpMessagingTemplate messagingTemplate;

    private static final String DEST_RESP_EVENT = "/queue/response.event.";

    public EventMessagingServiceImpl(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void send(EventDto event) {
        var eventCloned = DtoUtil.cloneEventDto(event);
        var destination = DEST_RESP_EVENT + eventCloned.getAccountId() + '-' + eventCloned.getAppSessionId();
        messagingTemplate.convertAndSend(destination, eventCloned);
    }
}
