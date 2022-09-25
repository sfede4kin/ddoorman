package ru.ddoorman.client.model.dto;

import ru.ddoorman.client.model.enumeration.EventTypeEnum;

import java.util.Date;
import java.util.UUID;

public class EventDto {

    private UUID id;
    private UUID refId;
    private Long accountId;
    private Long keyId;
    private Long doorId;
    private Date ts;
    private EventTypeEnum type;

    public EventDto(){};

    public EventDto(UUID id, UUID refId, Long accountId, Long keyId, Long doorId, Date ts, EventTypeEnum type) {
        this.id = id;
        this.refId = refId;
        this.accountId = accountId;
        this.keyId = keyId;
        this.doorId = doorId;
        this.ts = ts;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRefId() {
        return refId;
    }

    public void setRefId(UUID refId) {
        this.refId = refId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public Long getDoorId() {
        return doorId;
    }

    public void setDoorId(Long doorId) {
        this.doorId = doorId;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public EventTypeEnum getType() {
        return type;
    }

    public void setType(EventTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", refId=" + refId +
                ", accountId=" + accountId +
                ", keyId=" + keyId +
                ", doorId=" + doorId +
                ", ts=" + ts +
                ", type=" + type +
                '}';
    }
}
