package ua.yyunikov.domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class EventRepositoryImpl implements EventRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public EventRepositoryImpl(@Autowired final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Event> findByTs(final Long from, final Long to, final Pageable pageable) {
        final Criteria baseCriteria = Criteria.where(Event.Meta.TS).gte(from);
        final Query query = to == null ? Query.query(baseCriteria).with(pageable) : Query.query(baseCriteria.lte(to)).with(pageable);
        return mongoTemplate.find(query, Event.class);
    }
}
