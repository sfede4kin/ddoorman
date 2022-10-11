package ru.ddoorman.client.model.dto;

public class KeyGroupDto {

    private Long id;
    private KeyDto key;

    public KeyGroupDto(Long id, KeyDto key) {
        this.id = id;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KeyDto getKey() {
        return key;
    }

    public void setKeys(KeyDto key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "KeyGroupDto{" +
                "id=" + id +
                ", key=" + key +
                '}';
    }
}
