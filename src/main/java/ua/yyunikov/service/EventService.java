package ua.yyunikov.service;

import ua.yyunikov.persistence.entity.EventType;

public interface EventService {
    Long countByType(final EventType type);
}
