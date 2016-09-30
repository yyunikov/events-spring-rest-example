package ua.yyunikov.integration.event.projection;

import org.springframework.data.rest.core.config.Projection;
import ua.yyunikov.integration.ProjectionName;
import ua.yyunikov.domain.event.Event;
import ua.yyunikov.domain.event.EventType;

import java.util.Map;

@Projection(name = ProjectionName.STANDARD, types = Event.class)
public interface StandardEventProjection {

    String getId();

    EventType getType();

    Long getTs();

    Map<String, Object> getParams();
}
