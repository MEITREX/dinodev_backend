package de.unistuttgart.iste.meitrex.scrumgame.controller.event;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.EventService;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.ReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final ReminderService reminderService;

    @SchemaMapping
    public Page<Event> events(
            Project project,
            @Argument int page,
            @Argument int size
    ) {
        reminderService.publishRemindersIfScheduled();
        Pageable pageable = PageRequest.of(page, size);
        return eventService.getAndSyncEvents(project, pageable);
    }

    @SchemaMapping
    public List<Event> issueEvents(Issue issue) {
        return eventService.getAndSyncEvents(issue);
    }

    @SchemaMapping
    public Page<Event> publicEvents(
            UserInProject user,
            @Argument int page,
            @Argument int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return eventService.getPublicUserEvents(user.getProjectId(), user.getUserId(), pageable);
    }

    @SchemaMapping
    public Event reactToEvent(ProjectMutation projectMutation, @Argument UUID eventId, @Argument String reaction) {
        return eventService.reactToEvent(projectMutation, eventId, reaction);
    }

    @SchemaMapping
    public Event postComment(
            ProjectMutation projectMutation,
            @Argument UUID optionalParentEventId,
            @Argument String comment
    ) {
        return eventService.addUserMessage(projectMutation, optionalParentEventId, comment);
    }

    @SchemaMapping
    public TemplateField field(DefaultEvent event, @Argument String name) {
        return eventService.findField(event, name).orElse(null);
    }

    @SchemaMapping
    public Integer xpForCurrentUser(DefaultEvent event) {
        return eventService.getXpForCurrentUser(event);
    }

    @SchemaMapping
    public List<Reaction> reactions(DefaultEvent event) {
        return eventService.getReactions(event);
    }

    @SchemaMapping
    public List<DefaultEvent> children(DefaultEvent event) {
        return eventService.getChildren(event);
    }

    @SubscriptionMapping
    public Flux<Event> event(@Argument UUID projectId, @Argument UUID userId) {
        return eventService.getEventFlux(projectId, userId);
    }
}
