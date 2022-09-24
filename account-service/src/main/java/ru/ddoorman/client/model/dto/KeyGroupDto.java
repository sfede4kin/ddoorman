package ru.ddoorman.client.model.dto;

import java.util.Set;

public class KeyGroupDto {

    private Long id;
    private Long keyId;
    private KeyDto key;

    public KeyGroupDto(Long id, Long keyId, KeyDto key) {
        this.id = id;
        this.keyId = keyId;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public KeyDto getKey() {
        return key;
    }

    public void setKey(KeyDto key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "KeyGroupDto{" +
                "id=" + id +
                ", keyId=" + keyId +
                ", key=" + key +
                '}';
    }
}
