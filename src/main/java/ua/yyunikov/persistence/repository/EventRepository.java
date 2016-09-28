package ua.yyunikov.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ua.yyunikov.persistence.entity.Event;
import ua.yyunikov.persistence.entity.EventType;
import ua.yyunikov.projections.StandardEventProjection;

@RepositoryRestResource(collectionResourceRel = Event.COLLECTION_NAME, path = Event.COLLECTION_NAME, excerptProjection = StandardEventProjection.class)
public interface EventRepository extends MongoRepository<Event, String> {

    @RestResource(exported = false)
    Long countByType(@Param(Event.Meta.PARAM_TYPE) final EventType type);

    Page<Event> findByType(@Param(Event.Meta.PARAM_TYPE) final EventType type, final Pageable pageable);
}
