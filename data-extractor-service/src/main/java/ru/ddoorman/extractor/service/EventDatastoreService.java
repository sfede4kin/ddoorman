package ru.ddoorman.extractor.service;

import ru.ddoorman.extractor.model.Event;

public interface EventDatastoreService {

    Event save(Event event);

}
