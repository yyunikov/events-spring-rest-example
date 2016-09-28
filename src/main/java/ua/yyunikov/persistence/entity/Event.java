package ua.yyunikov.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = Event.COLLECTION_NAME)
public class Event implements Persistable, Storable {

    public static final String COLLECTION_NAME = "events";

    @Id
    private String id;

    private EventType type;

    @CreatedDate
    private Long ts;

    private Map<String, Object> params;

    @PersistenceConstructor
    public Event(final EventType type, final Map<String, Object> params) {
        this.type = type;
        this.params = params;
    }

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public EventType getType() {
        return type;
    }

    public void setType(final EventType type) {
        this.type = type;
    }

    @Override
    public Long getTs() {
        return ts;
    }

    public void setTs(final Long ts) {
        this.ts = ts;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(final Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Event event = (Event) o;

        if (!id.equals(event.id)) return false;
        if (type != event.type) return false;
        if (ts != null ? !ts.equals(event.ts) : event.ts != null) return false;
        return params != null ? params.equals(event.params) : event.params == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (ts != null ? ts.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    public interface Meta {
        String PARAM_TYPE = "type";
    }
}
