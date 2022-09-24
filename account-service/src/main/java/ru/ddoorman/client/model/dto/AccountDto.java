package ru.ddoorman.client.model.dto;

import java.util.Set;

public class AccountDto {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private Set<KeyGroupDto> keyGroups;
    private Set<DoorGroupDto> doorGroups;

    public AccountDto(Long id, String name, String address, String phone, Set<KeyGroupDto> keyGroups,
                      Set<DoorGroupDto> doorGroups) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.keyGroups = keyGroups;
        this.doorGroups = doorGroups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<KeyGroupDto> getKeyGroups() {
        return keyGroups;
    }

    public void setKeyGroups(Set<KeyGroupDto> keyGroups) {
        this.keyGroups = keyGroups;
    }

    public Set<DoorGroupDto> getDoorGroups() {
        return doorGroups;
    }

    public void setDoorGroups(Set<DoorGroupDto> doorGroups) {
        this.doorGroups = doorGroups;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", keyGroups=" + keyGroups +
                ", doorGroups=" + doorGroups +
                '}';
    }
}
