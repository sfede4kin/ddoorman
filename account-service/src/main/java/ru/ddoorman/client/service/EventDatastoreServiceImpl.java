package ru.ddoorman.client.service;

import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.Event;
import ru.ddoorman.client.repository.EventRepository;

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
