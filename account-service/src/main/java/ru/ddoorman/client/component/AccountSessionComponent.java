package ru.ddoorman.client.component;

public interface AccountSessionComponent {
    void put(String sessionId, Long accountId);
    void remove(String sessionId);
    boolean containsValue(Long accountId);
    String toString();

}
