package ua.yyunikov.projections;

import org.springframework.data.rest.core.config.Projection;
import ua.yyunikov.persistence.entity.Event;
import ua.yyunikov.persistence.entity.EventType;

import java.util.Map;

@Projection(name = ProjectionName.STANDARD, types = Event.class)
public interface StandardEventProjection {

    String getId();

    EventType getType();

    Long getTs();

    Map<String, Object> getParams();
}
