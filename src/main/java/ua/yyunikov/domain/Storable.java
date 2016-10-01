package ua.yyunikov.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

public interface Storable extends Persistable {

    String getId();

    Long getTs();

    @JsonIgnore
    default boolean isNew() {
        return getTs() == null;
    }

    @JsonIgnore
    String getCollectionName();
}
