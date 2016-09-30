package ua.yyunikov.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.yyunikov.domain.event.Event;
import ua.yyunikov.domain.event.EventType;
import ua.yyunikov.domain.event.EventRepository;

import java.util.HashMap;
import java.util.UUID;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private EventRepository eventRepository;

    public DatabaseLoader(@Autowired final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(final String... args) throws Exception {
        final HashMap<String, Object> linkClickedParams = new HashMap<>();
        linkClickedParams.put("name", "Google");
        linkClickedParams.put("path", "http://google.com");
        linkClickedParams.put("priority", 8);

        final HashMap<String, Object> sessionStartParams = new HashMap<>();
        sessionStartParams.put("sessionId", UUID.randomUUID().toString());

        if (eventRepository.countByType(EventType.SESSION_START) == 0) {
            this.eventRepository.save(new Event(EventType.SESSION_START, sessionStartParams));
        }

        if (eventRepository.countByType(EventType.LINK_CLICKED) == 0) {
            this.eventRepository.save(new Event(EventType.LINK_CLICKED, linkClickedParams));
        }
    }
}
