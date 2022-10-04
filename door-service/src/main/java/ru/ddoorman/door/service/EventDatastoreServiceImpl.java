package ru.ddoorman.door.service;

import org.springframework.stereotype.Service;
import ru.ddoorman.door.model.Event;
import ru.ddoorman.door.repository.EventRepository;

@Service
public class EventDatastoreServiceImpl implements EventDatastoreService {
    private final EventRepository eventRepository;

    public EventDatastoreServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event){
        return eventRepository.save(event);
    }
}
