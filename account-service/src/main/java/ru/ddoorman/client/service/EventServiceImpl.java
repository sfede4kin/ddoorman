package ru.ddoorman.client.service;

import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.Event;
import ru.ddoorman.client.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event){
        return eventRepository.save(event);
    }
}
