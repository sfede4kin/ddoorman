package ru.ddoorman.door.event.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ddoorman.door.event.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

    Event save(Event event);
}
