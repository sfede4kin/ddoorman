package ru.ddoorman.door.event.service;

import org.springframework.stereotype.Service;
import ru.ddoorman.door.event.repository.EventRepository;
import ru.ddoorman.door.event.model.Event;

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
