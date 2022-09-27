package ru.ddoorman.client.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ddoorman.client.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

    Event save(Event event);
}
