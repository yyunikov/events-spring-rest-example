package ua.yyunikov;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import ua.yyunikov.facade.validation.EventValidator;
import ua.yyunikov.persistence.entity.Event;
import ua.yyunikov.facade.projection.MinimalEventProjection;
import ua.yyunikov.facade.projection.StandardEventProjection;

@Configuration
public class ApplicationConfig {

    @Bean
    public SpelAwareProxyProjectionFactory projectionFactory() {
        return new SpelAwareProxyProjectionFactory();
    }

    @Configuration
    public static class CustomRepositoryRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

        @Override
        public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config) {
            config.exposeIdsFor(Event.class);
            config.getProjectionConfiguration().addProjection(MinimalEventProjection.class);
            config.getProjectionConfiguration().addProjection(StandardEventProjection.class);
        }

        @Override
        public void configureValidatingRepositoryEventListener(final ValidatingRepositoryEventListener validatingListener) {
            validatingListener.addValidator("beforeCreate", new EventValidator());
            validatingListener.addValidator("beforeSave", new EventValidator());
        }
    }
}
