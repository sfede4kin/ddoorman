package ru.ddoorman.door.service;

import org.springframework.stereotype.Service;
import ru.ddoorman.door.model.Event;
import ru.ddoorman.door.repository.EventRepository;

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
