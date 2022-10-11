package ru.ddoorman.door.event.model.dto;

import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.door.event.model.Event;

import java.sql.Timestamp;

public class DtoUtil {
    public static Event cloneEventDtoToEvent(EventDto event) {
        return new Event(event.getSourceId(), event.getRefId(), event.getAccountId(),
                event.getKeyId(), event.getDoorId(), Timestamp.valueOf(event.getTs()), event.getType().name());
    }
    public static EventDto cloneEventDto(EventDto event){
        return new EventDto(event.getSourceId(), event.getRefId(), event.getAccountId(), event.getKeyId(),
                event.getDoorId(), event.getTs(), event.getType(), event.getAppSessionId());
    }
}
