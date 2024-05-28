package de.unistuttgart.iste.meitrex.scrumgame.controller.event;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.Issue;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.generated.dto.UserInProject;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @SchemaMapping
    public Page<Event> events(
            Project project,
            @Argument int page,
            @Argument int size
    ) {
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
}
