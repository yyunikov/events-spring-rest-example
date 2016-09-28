package ua.yyunikov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;
import ua.yyunikov.persistence.entity.EventType;
import ua.yyunikov.service.EventService;

@RepositoryRestController
public class EventController {

    private final EventService eventService;

    public EventController(@Autowired final EventService eventService) {
        this.eventService = eventService;
    }

    @ResponseBody
    @RequestMapping(value = ResourcePath.Events.COUNT_RESOURCE, method = RequestMethod.GET, produces = MediaTypeAdditional.HAL_JSON)
    public PersistentEntityResource count(@RequestParam("type") EventType type, final PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(eventService.countByType(type));
    }
}
