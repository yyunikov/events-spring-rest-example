package ua.yyunikov.service;

import ua.yyunikov.persistence.entity.Event;

import java.util.List;

public interface EventService {

    /**
     * Finds all events by the timestamp in specified 'from'-'to' timestamp period.
     * If 'to' parameter is not specified - all events from 'from' timestamp will be returned.
     * Supports pagination by passing page number and size variables.
     *
     * @param from timestamp from which to find events
     * @param to timestamp until which to find events (optional)
     * @param page page number for pagination
     * @param size size of returned results
     * @return list of events in specified time range
     */
    List<Event> findByTs(final Long from, final Long to, final int page, final int size);
}
