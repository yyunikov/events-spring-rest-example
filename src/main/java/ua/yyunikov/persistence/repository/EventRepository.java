package ua.yyunikov.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.yyunikov.persistence.entity.Event;

import java.util.stream.Stream;

@RepositoryRestResource(collectionResourceRel = Event.COLLECTION_NAME, path = Event.COLLECTION_NAME)
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Stream<Event> countByType(@Param(Event.Meta.PARAM_TYPE) final String type);
}
