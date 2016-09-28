package ua.yyunikov.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

interface Storable extends Persistable {

    @JsonIgnore
    Long getTs();

    @JsonIgnore
    default boolean isNew() {
        return getTs() == null;
    }

    @JsonIgnore
    String getCollectionName();
}
