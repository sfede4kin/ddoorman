package ru.ddoorman.door.event.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("door_event")
public class Event {
    @Id
    private final Long id;
    private final String sourceId;
    private final String refId;
    private final Long accountId;
    private final Long keyId;
    private final Long doorId;
    private final Timestamp ts;
    private final String type;
    @Transient
    private final boolean isNew;

    public Event(Long id, String sourceId, String refId, Long accountId, Long keyId, Long doorId, Timestamp ts, String type, boolean isNew) {
        this.id = id;
        this.sourceId = sourceId;
        this.refId = refId;
        this.accountId = accountId;
        this.keyId = keyId;
        this.doorId = doorId;
        this.ts = ts;
        this.type = type;
        this.isNew = isNew;
    }

    @PersistenceCreator
    public Event(Long id, String sourceId, String refId, Long accountId, Long keyId, Long doorId, Timestamp ts, String type){
        this(id, sourceId, refId, accountId, keyId, doorId, ts, type, id == null);
    }

    public Event(String sourceId, String refId, Long accountId, Long keyId, Long doorId, Timestamp ts, String type){
        this(null, sourceId, refId, accountId, keyId, doorId, ts, type, true);
    }

    public Long getId(){
        return id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getRefId() {
        return refId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getKeyId() {
        return keyId;
    }

    public Long getDoorId() {
        return doorId;
    }

    public Timestamp getTs() {
        return ts;
    }

    public String getType() {
        return type;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", sourceId=" + sourceId +
                ", refId=" + refId +
                ", accountId=" + accountId +
                ", keyId=" + keyId +
                ", doorId=" + doorId +
                ", ts=" + ts +
                ", type=" + type +
                '}';
    }
}
