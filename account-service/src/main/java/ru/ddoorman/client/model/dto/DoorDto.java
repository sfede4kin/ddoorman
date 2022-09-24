package ru.ddoorman.client.model.dto;

public class DoorDto {

    private Long id;
    private String location;

    public DoorDto(Long id, String type) {
        this.id = id;
        this.location = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "DoorDto{" +
                "id=" + id +
                ", location=" + location +
                '}';
    }
}
