package ru.ddoorman.client.model.dto;

import ru.ddoorman.client.model.enumeration.EventTypeEnum;

import java.time.LocalDateTime;

public class EventDto{

    private String sourceId;
    private String refId;
    private Long accountId;
    private Long keyId;
    private Long doorId;
    private LocalDateTime ts;
    private EventTypeEnum type;
    private String appSessionId;

    public EventDto() {
    }

    ;

    public EventDto(String sourceId, String refId, Long accountId, Long keyId, Long doorId, LocalDateTime ts,
                    EventTypeEnum type, String appSessionId) {
        this.sourceId = sourceId;
        this.refId = refId;
        this.accountId = accountId;
        this.keyId = keyId;
        this.doorId = doorId;
        this.ts = ts;
        this.type = type;
        this.appSessionId = appSessionId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
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

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    public EventTypeEnum getType() {
        return type;
    }

    public void setType(EventTypeEnum type) {
        this.type = type;
    }

    public String getAppSessionId() {
        return appSessionId;
    }

    public void setAppSessionId(String appSessionId) {
        this.appSessionId = appSessionId;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "sourceId='" + sourceId + '\'' +
                ", refId='" + refId + '\'' +
                ", accountId=" + accountId +
                ", keyId=" + keyId +
                ", doorId=" + doorId +
                ", ts=" + ts +
                ", type=" + type +
                ", appSessionId='" + appSessionId + '\'' +
                '}';
    }
}
