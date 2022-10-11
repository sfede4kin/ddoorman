package ru.ddoorman.door.event.service;

import ru.ddoorman.client.model.dto.EventDto;

public interface KafkaConsumerService {
    void consume(EventDto event);
}
