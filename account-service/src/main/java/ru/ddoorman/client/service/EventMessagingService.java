package ru.ddoorman.client.service;

import ru.ddoorman.client.model.dto.EventDto;

public interface EventMessagingService {
    void send(EventDto event);
}
