package ru.ddoorman.door.component;

public interface DoorSessionComponent {
    void put(String sessionId, Long doorId);
    void remove(String sessionId);
    boolean containsValue(Long doorId);
    String toString();

}
