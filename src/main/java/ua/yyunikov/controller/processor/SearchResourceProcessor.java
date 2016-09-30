package ua.yyunikov.controller.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import ua.yyunikov.controller.ResourceParam;
import ua.yyunikov.controller.ResourcePath;
import ua.yyunikov.persistence.entity.Event;
import ua.yyunikov.utils.ResourceUtils;

@Component
public class SearchResourceProcessor implements ResourceProcessor<RepositorySearchesResource> {

    private final EntityLinks entityLinks;

    public SearchResourceProcessor(@Autowired final EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public RepositorySearchesResource process(final RepositorySearchesResource resource) {
        if (resource.getDomainType().equals(Event.class)) {
            // add searching by timestamp operation
            final LinkBuilder linkBuilder = entityLinks.linkFor(Event.class);
            final String paramsString = ResourceUtils.createParamsString(ResourceParam.FROM, ResourceParam.TO, ResourceParam.PAGE, ResourceParam.SIZE);
            resource.add(new Link(linkBuilder.toString() + ResourcePath.Events.SEARCH_TS + paramsString, ResourcePath.Events.Rel.TS));
        }
        return resource;
    }
}
