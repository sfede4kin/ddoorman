package ru.ddoorman.extractor.model.dto;

import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.client.model.enumeration.EventTypeEnum;
import ru.ddoorman.extractor.model.Event;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class DtoUtil {
    public static Event cloneEventDtoToEvent(EventDto event) {
        return new Event(event.getSourceId(), event.getRefId(), event.getAccountId(),
                event.getKeyId(), event.getDoorId(), Timestamp.valueOf(event.getTs()), event.getType().name());
    }
    public static EventDto getResponseEventDto(EventDto event, EventTypeEnum type){
        return new EventDto(UUID.randomUUID().toString(),event.getSourceId(), event.getAccountId(), event.getKeyId(),
                            event.getDoorId(), LocalDateTime.now(ZoneOffset.UTC), type, event.getAppSessionId());
    }
    public static EventDto cloneEventDto(EventDto event){
        return new EventDto(event.getSourceId(), event.getRefId(), event.getAccountId(), event.getKeyId(),
                event.getDoorId(), event.getTs(), event.getType(), event.getAppSessionId());
    }
}
