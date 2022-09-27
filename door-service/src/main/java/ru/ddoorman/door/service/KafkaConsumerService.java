package ru.ddoorman.door.service;

import ru.ddoorman.client.model.dto.EventDto;

public interface KafkaConsumerService {
    void consume(EventDto event);
}
