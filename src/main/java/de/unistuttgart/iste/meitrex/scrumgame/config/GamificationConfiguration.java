package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.rulesengine.EventTypeRegistry;
import de.unistuttgart.iste.meitrex.rulesengine.GamificationEngine;
import de.unistuttgart.iste.meitrex.rulesengine.Rule;
import de.unistuttgart.iste.meitrex.rulesengine.RuleRegistry;
import de.unistuttgart.iste.meitrex.rulesengine.util.DefaultEventPublisher;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.crs.CrsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.EventPersistenceService;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.ScrumGameEventTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Slf4j
@Configuration
public class GamificationConfiguration {

    @Bean
    public DefaultEventPublisher eventPublisher(EventPersistenceService eventService) {
        return new DefaultEventPublisher(eventService);
    }

    @Bean
    public EventTypeRegistry eventTypeRegistry() {
        EventTypeRegistry eventTypeRegistry = new EventTypeRegistry();

        registerEventTypes(eventTypeRegistry);

        return eventTypeRegistry;
    }

    @Bean
    RuleRegistry ruleRegistry(ApplicationContext applicationContext) {
        RuleRegistry ruleRegistry = new RuleRegistry();

        Map<String, Rule> ruleBeans = applicationContext.getBeansOfType(Rule.class);

        // Register each Rule bean
        for (Rule rule : ruleBeans.values()) {
            ruleRegistry.register(rule);
        }

        return ruleRegistry;
    }

    @Bean
    public GamificationEngine gamificationEngine(
            EventPublisher<Event, CreateEventInput> eventPublisher,
            EventTypeRegistry eventTypeRegistry,
            RuleRegistry ruleRegistry
    ) {
        return new GamificationEngine(eventPublisher, ruleRegistry, eventTypeRegistry);
    }

    private void registerEventTypes(EventTypeRegistry eventTypeRegistry) {

        ImsEventTypes.allEventTypes().forEach(eventTypeRegistry::register);
        CrsEventTypes.allEventTypes().forEach(eventTypeRegistry::register);
        ScrumGameEventTypes.allEventTypes().forEach(eventTypeRegistry::register);

        // add other event types as needed
    }

}
