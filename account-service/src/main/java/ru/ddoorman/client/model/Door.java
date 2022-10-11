package ru.ddoorman.client.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table("door")
public class Door {
    @Id
    private final Long id;
    private final String location;
    @Transient
    private final boolean isNew;

    public Door(Long id, String location, boolean isNew) {
        this.id = id;
        this.location = location;
        this.isNew = isNew;
    }
    @PersistenceCreator
    public Door(Long id, String location){
        this(id, location, true);
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return "Door{" +
                "id=" + id +
                ", location='" + location + '\'' +
                '}';
    }
}
