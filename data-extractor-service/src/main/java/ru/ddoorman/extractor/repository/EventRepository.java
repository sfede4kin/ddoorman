package ru.ddoorman.extractor.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ddoorman.extractor.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

    Event save(Event event);
}
