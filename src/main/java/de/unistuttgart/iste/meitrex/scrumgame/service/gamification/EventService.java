package de.unistuttgart.iste.meitrex.scrumgame.service.gamification;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.Issue;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.EventEntity;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
@Getter(AccessLevel.PROTECTED)
public class EventService {

    private final AuthService                             authService;
    private final ImsService                              imsService;
    private final EventPublisher<Event, CreateEventInput> eventPublisher;
    private final EventPersistenceService                 eventPersistenceService;

    public Page<Event> getAndSyncEvents(Project project, Pageable pageable) {
        syncEventsFromGropius(project);

        Page<EventEntity> events = eventPersistenceService.getRepository()
                .findAllForUser(project.getId(), authService.getCurrentUserId(), pageable);

        return events.map(eventPersistenceService::convertToDto);
    }

    public Page<Event> getPublicUserEvents(UUID projectId, UUID userId, Pageable pageable) {

        return eventPersistenceService.getRepository()
                .findPublicEventsForUser(projectId, userId, pageable)
                .map(eventPersistenceService::convertToDto);
    }

    public void syncEventsFromGropius(Project project) {
        // Note that this method could be implemented in a more efficient way
        // by fetching all issues with timeline items in a single request,
        // however, with this approach we can consider the last sync date
        // of each issue individually and avoid fetching unnecessary data,
        // which is difficult to do with a single request.
        List<Issue> issues = imsService.getIssues(project);
        issues.forEach(this::syncEvents);
    }

    public List<Event> getAndSyncEvents(Issue issue) {
        syncEvents(issue);

        return eventPersistenceService.getRepository().findForIssue(issue.getId())
                .stream()
                .map(eventPersistenceService.getEventFactory()::createDefaultEvent)
                .map(Event.class::cast)
                .toList();
    }

    /**
     * Returns a Flux of events for a given project.
     * The Flux will emit events as they are published. It will not emit events that were published before the
     * subscription.
     *
     * @param projectId The project id to subscribe to
     * @return A Flux of events for the given project
     */
    public Flux<Event> getEventFlux(UUID projectId) {
        return eventPublisher.getEventStream()
                .filter(event -> event.getProjectId().equals(projectId));
    }

    private void syncEvents(Issue issue) {
        Optional<EventEntity> lastSynchronizedEvent
                = eventPersistenceService.getRepository().findLastSyncForIssue(issue.getId());

        imsService.getEventsForIssue(issue,
                        lastSynchronizedEvent.map(Event::getTimestamp)
                                .orElse(LocalDate.of(1, 1, 1).atStartOfDay().atOffset(ZoneOffset.UTC)))
                .forEach(eventPublisher::publishEvent);
    }
}