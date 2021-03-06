package ua.yyunikov.domain.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ua.yyunikov.integration.ResourcePath;
import ua.yyunikov.integration.event.projection.StandardEventProjection;

@RepositoryRestResource(collectionResourceRel = Event.COLLECTION_NAME, path = Event.COLLECTION_NAME, excerptProjection = StandardEventProjection.class)
public interface EventRepository extends MongoRepository<Event, String>, EventRepositoryCustom {

    @RestResource(exported = false)
    Long countByType(@Param(Event.Meta.TYPE) final EventType type);

    @RestResource(path = ResourcePath.Events.TYPE, rel = Event.Meta.TYPE)
    Page<Event> findByType(@Param(Event.Meta.TYPE) final EventType type, final Pageable pageable);
}
