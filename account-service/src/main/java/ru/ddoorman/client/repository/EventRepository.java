package ru.ddoorman.client.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ddoorman.client.model.Event;

import java.util.UUID;

public interface EventRepository extends CrudRepository<Event, UUID> {

    Event save(Event event);
}
