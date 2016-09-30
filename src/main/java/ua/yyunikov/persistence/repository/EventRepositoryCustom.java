package ua.yyunikov.persistence.repository;

import org.springframework.data.domain.Pageable;
import ua.yyunikov.persistence.entity.Event;

import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findByTs(final Long from, final Long to, final Pageable pageable);
}
