package ru.ddoorman.door.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ddoorman.door.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

    Event save(Event event);
}
