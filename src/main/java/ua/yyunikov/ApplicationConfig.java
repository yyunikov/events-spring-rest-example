package ua.yyunikov;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import ua.yyunikov.persistence.entity.Event;
import ua.yyunikov.projections.MinimalEventProjection;
import ua.yyunikov.projections.StandardEventProjection;

@Configuration
public class ApplicationConfig {

    public static class CustomRepositoryRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

        @Override
        public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config) {
            config.exposeIdsFor(Event.class);
            config.getProjectionConfiguration().addProjection(MinimalEventProjection.class);
            config.getProjectionConfiguration().addProjection(StandardEventProjection.class);
        }
    }
}
