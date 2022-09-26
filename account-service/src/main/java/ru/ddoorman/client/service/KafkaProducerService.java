package ru.ddoorman.client.service;

public interface KafkaProducerService {
    void sendMessage(String msg);
}
