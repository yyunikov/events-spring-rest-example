package ua.yyunikov.controller.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import ua.yyunikov.controller.EventController;
import ua.yyunikov.controller.ResourcePath;
import ua.yyunikov.persistence.entity.Event;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EventResourceProcessor implements ResourceProcessor<Resource<Event>> {

    private final RepositoryEntityLinks entityLinks;

    public EventResourceProcessor(@Autowired final RepositoryEntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public Resource<Event> process(final Resource<Event> resource) {
        // TODO why process is not called?
        final Event event = resource.getContent();
        // resource.add(entityLinks.linkForSingleResource(Event.class, event.getId()).withRel(ResourcePath.Events.COUNT));
        resource.add(linkTo(methodOn(EventController.class).count(event.getType(), null)).withRel(ResourcePath.Events.COUNT));
        return resource;
    }
}
