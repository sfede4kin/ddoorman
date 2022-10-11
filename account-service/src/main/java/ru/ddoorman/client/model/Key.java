package ru.ddoorman.client.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table("door_key")
public class Key {
    @Id
    private final Long id;
    private final String type;
    @Transient
    private final boolean isNew;

    public Key(Long id, String type, boolean isNew){
        this.id = id;
        this.type = type;
        this.isNew = isNew;
    }
    @PersistenceCreator
    public Key(Long id, String type){
        this(id, type, true);
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
