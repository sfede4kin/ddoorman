package ru.ddoorman.client.model.dto;

public class KeyDto {

    private Long id;
    private String type;

    public KeyDto(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "KeyDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
