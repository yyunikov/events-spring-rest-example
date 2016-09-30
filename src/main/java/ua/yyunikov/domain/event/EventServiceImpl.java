package ua.yyunikov.domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepositoryCustom eventRepository;

    public EventServiceImpl(@Autowired final EventRepositoryCustom eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Event> findByTs(final Long from, final Long to, final int page, final int size) {
        return eventRepository.findByTs(from, to, new PageRequest(page, size));
    }
}
