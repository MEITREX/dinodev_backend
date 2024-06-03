package de.unistuttgart.iste.meitrex.scrumgame.service.event;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.rulesengine.DefaultEventTypes;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.EventEntity;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsService;
import de.unistuttgart.iste.meitrex.scrumgame.util.TemplateDataUtils;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static de.unistuttgart.iste.meitrex.scrumgame.util.TemplateDataUtils.findIntField;
import static de.unistuttgart.iste.meitrex.scrumgame.util.TemplateDataUtils.findStringField;

@Slf4j
@RequiredArgsConstructor
@Service
@Getter(AccessLevel.PROTECTED)
public class EventService {

    private final AuthService                             authService;
    private final ImsService                              imsService;
    private final EventPublisher<Event, CreateEventInput> eventPublisher;
    private final EventPersistenceService                 eventPersistenceService;

    // min time between event syncs
    private static final Duration GLOBAL_SYNC_INTERVAL = Duration.ofMinutes(2);

    private OffsetDateTime lastGlobalEventSync = OffsetDateTime.MIN;

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
        if (Duration.between(lastGlobalEventSync, OffsetDateTime.now()).abs().compareTo(GLOBAL_SYNC_INTERVAL) < 0) {
            return;
        }

        // Note that this method could be implemented in a more efficient way
        // by fetching all issues with timeline items in a single request,
        // however, with this approach we can consider the last sync date
        // of each issue individually and avoid fetching unnecessary data,
        // which is difficult to do with a single request.
        List<Issue> issues = imsService.getIssues(project);
        issues.forEach(this::syncEvents);

        lastGlobalEventSync = OffsetDateTime.now();
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
     * @param userId    The user id to filter events for
     * @return A Flux of events for the given project
     */
    public Flux<Event> getEventFlux(UUID projectId, UUID userId) {
        return eventPublisher.getEventStream()
                .filter(event -> {
                    if (event.getProjectId() == null || !event.getProjectId().equals(projectId)) {
                        return false;
                    }
                    return event.getVisibility() == EventVisibility.PUBLIC
                           || event.getUserId().equals(userId)
                           || event.getVisibleToUserIds().contains(userId);
                });
    }

    public Event reactToEvent(ProjectMutation projectMutation, UUID eventId, String reaction) {
        CreateEventInput input = CreateEventInput.builder()
                .setProjectId(projectMutation.getProject().getId())
                .setUserId(authService.getCurrentUserId())
                .setParentId(eventId)
                .setEventData(List.of(
                        TemplateFieldInput.builder()
                                .setType(AllowedDataType.STRING)
                                .setKey("reaction")
                                .setValue(reaction)
                                .build()
                ))
                .setEventTypeIdentifier(ScrumGameEventTypes.EVENT_REACTION.getIdentifier())
                .build();

        return eventPublisher.publishEvent(input);
    }

    public Event addUserMessage(ProjectMutation projectMutation, @Nullable UUID optionalParentId, String message) {
        CreateEventInput input = CreateEventInput.builder()
                .setProjectId(projectMutation.getProject().getId())
                .setUserId(authService.getCurrentUserId())
                .setParentId(optionalParentId)
                .setEventData(List.of(
                        TemplateFieldInput.builder()
                                .setType(AllowedDataType.STRING)
                                .setKey("message")
                                .setValue(message)
                                .build()
                ))
                .setEventTypeIdentifier(DefaultEventTypes.USER_MESSAGE.getIdentifier())
                .build();

        return eventPublisher.publishEvent(input);
    }

    public Optional<? extends TemplateField> findField(Event event, String name) {
        return TemplateDataUtils.findField(event, name);
    }

    public List<DefaultEvent> getChildren(DefaultEvent event) {
        return eventPersistenceService.getRepository().findChildren(event.getId(), authService.getCurrentUserId())
                .stream()
                .map(eventPersistenceService::convertToDto)
                .toList();
    }

    public List<Reaction> getReactions(DefaultEvent event) {
        return getChildren(event).stream()
                .filter(child ->
                        child.getEventType().getIdentifier()
                                .equals(ScrumGameEventTypes.EVENT_REACTION.getIdentifier()))
                .map(childEvent -> Reaction.builder()
                        .setReaction(findStringField(childEvent, "reaction").orElse(""))
                        .setUserId(childEvent.getUserId())
                        .build())
                .distinct()
                .toList();
    }

    public Integer getXpForCurrentUser(DefaultEvent event) {
        return getChildren(event).stream()
                .filter(child -> child.getEventType().getIdentifier()
                        .equals(ScrumGameEventTypes.XP_GAIN.getIdentifier()))
                .filter(child -> child.getUserId().equals(authService.getCurrentUserId()))
                .mapToInt(child -> findIntField(child, "xp").orElse(0))
                .sum();
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