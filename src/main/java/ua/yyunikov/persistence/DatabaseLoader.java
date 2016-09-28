package ua.yyunikov.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.yyunikov.persistence.entity.Event;
import ua.yyunikov.persistence.entity.EventType;
import ua.yyunikov.persistence.repository.EventRepository;

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

        this.eventRepository.save(new Event(EventType.LINK_CLICKED, linkClickedParams));
        this.eventRepository.save(new Event(EventType.SESSION_START, sessionStartParams));
    }
}
