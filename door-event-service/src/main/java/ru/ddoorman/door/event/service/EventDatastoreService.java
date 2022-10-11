package ru.ddoorman.door.event.service;

import ru.ddoorman.door.event.model.Event;

public interface EventDatastoreService {

    Event save(Event event);

}
