package ru.ddoorman.door.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.ddoorman.door.model.dto.DtoUtil;
import ru.ddoorman.client.model.dto.EventDto;

@Service
public class EventMessagingServiceImpl implements EventMessagingService {
    private final SimpMessagingTemplate messagingTemplate;

    private static final String DEST_QUEUE_EVENT = "/queue/event.";

    public EventMessagingServiceImpl(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void send(EventDto event) {
        var eventCloned = DtoUtil.cloneEventDto(event);
        var destination = DEST_QUEUE_EVENT + eventCloned.getDoorId();
        messagingTemplate.convertAndSend(destination, eventCloned);
    }
}
