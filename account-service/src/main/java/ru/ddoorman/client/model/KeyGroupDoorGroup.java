package ru.ddoorman.client.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("key_group_door_group")
public class KeyGroupDoorGroup {

    private final Long keyGroupId;
    @Id
    private final Long doorGroupId;
    @MappedCollection(idColumn = "id")
    private final Set<DoorGroup> doorGroups;
    @Transient
    private final boolean isNew;

    public KeyGroupDoorGroup(Long keyGroupId, Long doorGroupId, Set<DoorGroup> doorGroups, boolean isNew) {
        this.keyGroupId = keyGroupId;
        this.doorGroupId = doorGroupId;
        this.doorGroups = doorGroups;
        this.isNew = isNew;
    }

    @PersistenceCreator
    public KeyGroupDoorGroup(Long keyGroupId, Long doorGroupId, Set<DoorGroup> doorGroups){
        this(keyGroupId, doorGroupId, doorGroups, true);
    }

    public Long getKeyGroupId() {
        return keyGroupId;
    }

    public Long getDoorGroupId() {
        return doorGroupId;
    }

    public boolean isNew() {
        return isNew;
    }

    public Set<DoorGroup> getDoorGroups() {
        return doorGroups;
    }

    @Override
    public String toString() {
        return "KeyGroupDoorGroup{" +
                "keyGroupId=" + keyGroupId +
                ", doorGroupId=" + doorGroupId +
                ", doorGroups=" + doorGroups +
                '}';
    }
}
