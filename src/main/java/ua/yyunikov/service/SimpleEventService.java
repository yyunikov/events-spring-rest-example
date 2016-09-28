package ua.yyunikov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yyunikov.persistence.entity.EventType;
import ua.yyunikov.persistence.repository.EventRepository;

@Service
public class SimpleEventService implements EventService {

    private final EventRepository eventRepository;

    public SimpleEventService(@Autowired final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Long countByType(final EventType type) {
        return eventRepository.countByType(type);
    }
}
