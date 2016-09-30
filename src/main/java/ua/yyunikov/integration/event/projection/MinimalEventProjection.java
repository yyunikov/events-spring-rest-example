package ua.yyunikov.integration.event.projection;

import org.springframework.data.rest.core.config.Projection;
import ua.yyunikov.integration.ProjectionName;
import ua.yyunikov.domain.event.Event;
import ua.yyunikov.domain.event.EventType;

@Projection(name = ProjectionName.MINIMAL, types = Event.class)
public interface MinimalEventProjection {

    String getId();

    EventType getType();
}
