package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.rulesengine.EventTypeRegistry;
import de.unistuttgart.iste.meitrex.rulesengine.GamificationEngine;
import de.unistuttgart.iste.meitrex.rulesengine.RuleRegistry;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules.SimpleTestRule;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.EventPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Slf4j
@Configuration
public class GamificationConfiguration {

    @Bean
    @Scope("singleton")
    public EventPublisher<Event, CreateEventInput> eventPublisher(EventPersistenceService eventService) {
        return new EventPublisher<>(eventService);
    }

    @Bean
    public EventTypeRegistry eventTypeRegistry() {
        return new EventTypeRegistry();
    }

    @Bean RuleRegistry ruleRegistry(SimpleTestRule simpleTestRule) {
        RuleRegistry ruleRegistry = new RuleRegistry();

        ruleRegistry.register(simpleTestRule);

        return ruleRegistry;
    }

    @Bean
    @Scope("singleton")
    public GamificationEngine gamificationEngine(
            EventPublisher<Event, CreateEventInput> eventPublisher,
            EventTypeRegistry eventTypeRegistry,
            RuleRegistry ruleRegistry
    ) {
        log.info("Creating gamification engine");
        GamificationEngine engine = new GamificationEngine(eventPublisher, ruleRegistry, eventTypeRegistry);

        registerEventTypes(engine.getEventTypeRegistry());

        return engine;
    }

    private void registerEventTypes(EventTypeRegistry eventTypeRegistry) {

        ImsEventTypes.allEventTypes().forEach(eventTypeRegistry::register);

        // TODO add other event types
    }

}
