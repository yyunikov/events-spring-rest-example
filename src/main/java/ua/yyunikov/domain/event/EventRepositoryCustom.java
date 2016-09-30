package ua.yyunikov.domain.event;

import org.springframework.data.domain.Pageable;
import ua.yyunikov.domain.event.Event;

import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findByTs(final Long from, final Long to, final Pageable pageable);
}
