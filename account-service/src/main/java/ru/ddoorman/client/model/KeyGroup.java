package ru.ddoorman.client.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("key_group")
public class KeyGroup {

    private final Long id;
    @Id
    private final Long keyId;
    @MappedCollection(idColumn = "id")
    private final Key key;

    @Transient
    private final boolean isNew;

    public KeyGroup(Long id, Long keyId, Key key, boolean isNew){
        this.id = id;
        this.keyId = keyId;
        this.key = key;
        this.isNew = isNew;
    }

    @PersistenceCreator
    public KeyGroup(Long id, Long keyId, Key key){
        this(id, keyId, key, true);
    }

    public Long getId() {
        return id;
    }

    public Long getKeyId() {
        return keyId;
    }

    public Key getKey() {
        return key;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return "KeyGroup{" +
                "id=" + id +
                ", keyId=" + keyId +
                ", key=" + key +
                '}';
    }
}
