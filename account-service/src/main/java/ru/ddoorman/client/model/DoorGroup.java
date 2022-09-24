package ru.ddoorman.client.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("door_group")
public class DoorGroup {

    private final Long id;
    @Id
    private final Long doorId;
    @MappedCollection(idColumn = "id")
    private final Door door;
    @Transient
    private final boolean isNew;

    public DoorGroup(Long id, Long doorId, Door door, boolean isNew) {
        this.id = id;
        this.doorId = doorId;
        this.door = door;
        this.isNew = isNew;
    }

    @PersistenceCreator
    public DoorGroup(Long id, Long doorId, Door door){
        this(id, doorId, door,true);
    }

    public Long getId() {
        return id;
    }

    public Long getDoorId() {
        return doorId;
    }

    public Door getDoor() {
        return door;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return "DoorGroup{" +
                "id=" + id +
                ", doorId=" + doorId +
                ", door=" + door +
                '}';
    }
}
