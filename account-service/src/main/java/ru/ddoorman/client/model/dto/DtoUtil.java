package ru.ddoorman.client.model.dto;

import ru.ddoorman.client.model.*;
import ru.ddoorman.client.model.enumeration.EventTypeEnum;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DtoUtil {
    public static AccountDto cloneAccountToDto(Account account) {
        return new AccountDto(account.getId(), account.getName(), account.getAddress(), account.getPhone(),
                cloneKeyGroupToDto(account.getKeyGroups()), cloneDoorGroupToDto(account.getDoorGroups()));
    }

    public static Set<KeyGroupDto> cloneKeyGroupToDto(Set<KeyGroup> keyGroups) {
        var keyGroupDto = new HashSet<KeyGroupDto>();
        keyGroups.forEach(k -> keyGroupDto.add(new KeyGroupDto(k.getId(), cloneKeyToDto(k.getKey()))));
        return keyGroupDto;
    }

    public static KeyDto cloneKeyToDto(Key key) {
        return new KeyDto(key.getId(), key.getType());
    }

    public static Set<DoorGroupDto> cloneDoorGroupToDto(Set<KeyGroupDoorGroup> doorGroups) {
        var doorGroupDto = new HashSet<DoorGroupDto>();
        doorGroups.forEach(k -> {
            k.getDoorGroups().forEach(k1 -> doorGroupDto.add(new DoorGroupDto(k1.getId(), cloneDoorToDto(k1.getDoor()))));
        });
        return doorGroupDto;
    }
    public static DoorDto cloneDoorToDto(Door door) {
        return new DoorDto(door.getId(), door.getLocation());
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
