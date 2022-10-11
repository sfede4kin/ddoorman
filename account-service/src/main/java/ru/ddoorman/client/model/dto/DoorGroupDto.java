package ru.ddoorman.client.model.dto;

public class DoorGroupDto {

    private Long id;
    private DoorDto door;

    public DoorGroupDto(Long id, DoorDto door) {
        this.id = id;
        this.door = door;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DoorDto getDoor() {
        return door;
    }

    public void setKeys(DoorDto door) {
        this.door = door;
    }

    @Override
    public String toString() {
        return "DoorGroupDto{" +
                "id=" + id +
                ", door=" + door +
                '}';
    }
}
