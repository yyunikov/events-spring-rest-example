package ua.yyunikov.integration.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yyunikov.integration.ResourceParam;
import ua.yyunikov.integration.ResourcePath;
import ua.yyunikov.integration.event.projection.StandardEventProjection;
import ua.yyunikov.domain.event.Event;
import ua.yyunikov.domain.event.EventService;
import ua.yyunikov.utils.MediaTypeAdditional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ExposesResourceFor(Event.class)
@RequestMapping(ResourcePath.Events.SELF)
public class EventController {

    private final EventService eventService;
    private final ProjectionFactory projectionFactory;
    private final PagedResourcesAssembler<StandardEventProjection> standardProjectionAssembler;

    public EventController(@Autowired final EventService eventService,
                           @Autowired final ProjectionFactory projectionFactory,
                           final PagedResourcesAssembler<StandardEventProjection> standardProjectionAssembler) {
        this.eventService = eventService;
        this.projectionFactory = projectionFactory;
        this.standardProjectionAssembler = standardProjectionAssembler;
    }

    @ResponseBody
    @RequestMapping(path = ResourcePath.Events.SEARCH_TS, method = RequestMethod.GET, produces = MediaTypeAdditional.HAL_JSON)
    public ResponseEntity<PagedResources<Resource<StandardEventProjection>>> findByTs(@RequestParam(ResourceParam.FROM) @NotNull final Long from,
                                                                    @RequestParam(value = ResourceParam.TO, required = false) final Long to,
                                                                    @RequestParam(value = ResourceParam.PAGE, required = false, defaultValue = "0") final int page,
                                                                    @RequestParam(value = ResourceParam.SIZE, required = false, defaultValue = "10") final int size) {
        final List<Event> events = eventService.findByTs(from, to, page, size);
        final List<StandardEventProjection> projections = events.stream()
                .map(event -> projectionFactory.createProjection(StandardEventProjection.class, event))
                .collect(Collectors.toList());

        return ResponseEntity.ok(standardProjectionAssembler.toResource(new PageImpl<>(projections)));
    }
}
