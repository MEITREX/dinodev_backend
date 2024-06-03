package de.unistuttgart.iste.meitrex.scrumgame.controller.event;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventTypeInput;
import de.unistuttgart.iste.meitrex.generated.dto.EventType;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateEventTypeInput;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.EventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class EventTypeController {

    private final EventTypeService eventTypeService;

    @QueryMapping
    public EventType eventType(@Argument String id) {
        return eventTypeService.findEventType(id).orElse(null);
    }

    @QueryMapping
    public List<EventType> eventTypes() {
        return eventTypeService.getAllEventTypes();
    }

    @MutationMapping
    public EventType createEventType(@Argument CreateEventTypeInput input) {
        return eventTypeService.createUserDefinedEventType(input);
    }

    @MutationMapping
    public EventType updateEventType(
            @Argument String id,
            @Argument UpdateEventTypeInput input
    ) {
        return eventTypeService.updateUserDefinedEventType(id, input);
    }

    @MutationMapping
    public boolean deleteEventType(@Argument String id) {
        return eventTypeService.deleteUserDefinedEventType(id);
    }
}
