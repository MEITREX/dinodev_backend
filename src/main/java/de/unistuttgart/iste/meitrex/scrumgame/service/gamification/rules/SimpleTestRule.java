package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.EventVisibility;
import de.unistuttgart.iste.meitrex.generated.dto.TemplateFieldInput;
import de.unistuttgart.iste.meitrex.rulesengine.DefaultEventTypes;
import de.unistuttgart.iste.meitrex.rulesengine.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class SimpleTestRule implements Rule {

    private static final UUID ID = UUID.fromString("3b539342-53db-4ce4-bcdc-8c4f7c040aa3");

    @Override
    public UUID getId() {
        return ID;
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of("ISSUE_COMPLETED");
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        return true; // Always trigger
    }

    @Override
    public Optional<CreateEventInput> executeAction(Event triggerEvent) {
        return Optional.of(
                CreateEventInput.builder()
                        .setUserId(triggerEvent.getUserId())
                        .setProjectId(triggerEvent.getProjectId())
                        .setEventTypeIdentifier(DefaultEventTypes.SYSTEM_MESSAGE.getIdentifier())
                        .setEventData(List.of(
                                TemplateFieldInput.builder()
                                        .setKey("message")
                                        .setValue("Congratulations! You have completed an issue!")
                                        .build()))
                        .setVisibility(EventVisibility.PUBLIC)
                        .setMessage("Congratulations! You have completed an issue!")
                        .build()
        );
    }

}
