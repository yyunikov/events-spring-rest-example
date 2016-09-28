package ua.yyunikov.projections;

import org.springframework.data.rest.core.config.Projection;
import ua.yyunikov.persistence.entity.Event;
import ua.yyunikov.persistence.entity.EventType;

@Projection(name = ProjectionName.MINIMAL, types = Event.class)
public interface MinimalEventProjection {

    String getId();

    EventType getType();
}
