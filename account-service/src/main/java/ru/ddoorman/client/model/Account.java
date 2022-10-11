package ru.ddoorman.client.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("account")
public class Account implements Persistable<Long> {

    @Id
    private final Long id;
    private final String name;
    private final String address;
    private final String phone;
    private final Long keyGroupId;

    @MappedCollection(idColumn = "id")
    private final Set<KeyGroup> keyGroups;
    @MappedCollection(idColumn = "key_group_id")
    private final Set<KeyGroupDoorGroup> doorGroups;
    @Transient
    private final boolean isNew;

    public Account(Long id, String name, String address, String phone, Long keyGroupId,
                   Set<KeyGroup> keyGroups, Set<KeyGroupDoorGroup> doorGroups, boolean isNew) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.keyGroupId = keyGroupId;
        this.keyGroups = keyGroups;
        this.doorGroups = doorGroups;
        this.isNew = isNew;
    }

    @PersistenceCreator
    public Account(Long id, String name, String address, String phone, Long keyGroupId,
                   Set<KeyGroup> keyGroups, Set<KeyGroupDoorGroup> doorGroups) {
        this(id, name, address, phone, keyGroupId, keyGroups, doorGroups, true);
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Long getKeyGroupId() {
        return keyGroupId;
    }

    public Set<KeyGroup> getKeyGroups() {
        return keyGroups;
    }

    public Set<KeyGroupDoorGroup> getDoorGroups() {
        return doorGroups;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", keyGroupId=" + keyGroupId +
                ", keyGroups=" + keyGroups +
                ", doorGroups=" + doorGroups +
                '}';
    }
}
