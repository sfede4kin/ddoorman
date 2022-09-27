package ru.ddoorman.door.service;

import ru.ddoorman.client.model.dto.EventDto;

public interface KafkaProducerService {
    void sendMessage(EventDto msg);
}
