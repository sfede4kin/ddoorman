package ru.ddoorman.client.model.dto;

import java.util.Set;

public class AccountDto {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private Long keyGroupId;
    private Set<KeyDto> keys;

    public AccountDto(Long id, String name, String address, String phone, Long keyGroupId, Set<KeyDto> keys) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.keyGroupId = keyGroupId;
        this.keys = keys;
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

    public Long getKeyGroupId() {
        return keyGroupId;
    }

    public void setKeyGroupId(Long keyGroupId) {
        this.keyGroupId = keyGroupId;
    }

    public Set<KeyDto> getKeys() {
        return keys;
    }

    public void setKeys(Set<KeyDto> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", keyGroupId=" + keyGroupId +
                ", keys=" + keys +
                '}';
    }
}
