package de.unistuttgart.iste.meitrex.scrumgame.service.meeting;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.rulesengine.util.NonPersistentEventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingAttendeeEmbeddable;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.MeetingRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.function.*;

@Service
@Slf4j
@Getter(AccessLevel.PROTECTED)
public class MeetingService extends AbstractCrudService<UUID, MeetingEntity, Meeting> {

    private final EventPublisher<Event, CreateEventInput> eventPublisher;
    private final AuthService                             authService;
    private final MeetingRepository                       repository;

    private final NonPersistentEventPublisher<Meeting> meetingSubscriptionPublisher
            = new NonPersistentEventPublisher<>();

    public MeetingService(EventPublisher<Event, CreateEventInput> eventPublisher,
            AuthService authService,
            MeetingRepository repository,
            ModelMapper modelMapper) {
        super(repository, modelMapper, MeetingEntity.class, Meeting.class);
        this.eventPublisher = eventPublisher;
        this.authService = authService;
        this.repository = repository;

        meetingSubscriptionPublisher.getEventStream().subscribe(meeting ->
                log.debug("Meeting updated: {}, {}", meeting.getMeetingType(), meeting.getProjectId()));
    }


    public Meeting joinMeeting(UUID projectId, MeetingType type) {
        return updateMeeting(projectId, type, meetingEntity ->
                updateMeetingAttendee(meetingEntity,
                        authService.getCurrentUserId(),
                        attendee -> attendee.setState(UserState.ONLINE)));
    }

    public Meeting promoteToMeetingLeader(UUID projectId, MeetingType type, UUID userId) {
        return updateMeeting(projectId, type, meetingEntity ->
                updateMeetingAttendee(meetingEntity,
                        userId,
                        attendee -> attendee.setRole(MeetingRole.MEETING_LEADER)));
    }

    public Meeting pingMeeting(UUID projectId, MeetingType type) {
        return updateMeeting(projectId, type, meetingEntity -> {
            UUID currentUserId = authService.getCurrentUserId();
            updateMeetingAttendee(meetingEntity, currentUserId, attendee -> attendee.setState(UserState.ONLINE));
        });
    }

    public Meeting leaveMeeting(UUID projectId, MeetingType type) {
        if (findActiveMeeting(projectId, type, Meeting.class).isEmpty()) {
            return null;
        }
        return updateMeeting(projectId, type, entity -> {
            UUID currentUserId = authService.getCurrentUserId();
            updateMeetingAttendee(entity, currentUserId, attendee -> attendee.setState(UserState.OFFLINE));
        });
    }

    /**
     * Retrieves a flux stream of the currently active  meeting for the given project ID that emits the current
     * state of the meeting followed by any updates to the planning meeting. If no active meeting is
     * found for the project ID, the stream will be empty until a new meeting is created.
     *
     * @param projectId the ID of the project for which to retrieve meeting updates
     * @return a Flux stream of updated Meeting objects
     * @implNote the meeting type parameter could maybe be removed in the future.
     */
    public <T extends Meeting> Flux<T> getMeetingUpdates(UUID projectId,
            MeetingType meetingType,
            Class<T> meetingClass) {
        try {
            Flux<T> meetingUpdatesFlux = meetingSubscriptionPublisher.getEventStream()
                    .filter(meeting -> meeting.getProjectId().equals(projectId) &&
                                       meeting.getMeetingType() == meetingType)
                    .ofType(meetingClass);

            Optional<T> activeMeeting = findActiveMeeting(projectId, meetingType, meetingClass);

            if (activeMeeting.isPresent()) {
                return meetingUpdatesFlux.startWith(activeMeeting.get());
            }

            return meetingUpdatesFlux;
        } catch (Exception e) {
            log.error("Error while getting standup meeting updates", e);
            return Flux.empty();
        }
    }

    public <T extends Meeting> Optional<T> findActiveMeeting(UUID projectId,
            MeetingType meetingType,
            Class<T> meetingClass) {
        return repository.findTopByProjectIdAndMeetingTypeAndActive(projectId, meetingType, true)
                .map(meeting -> getModelMapper().map(meeting, meetingClass));
    }

    public List<MeetingAttendeeEmbeddable> initMeetingAttendees(UUID meetingLeaderId) {
        List<MeetingAttendeeEmbeddable> attendees = new ArrayList<>();
        attendees.add(MeetingAttendeeEmbeddable.builder()
                .setUserId(meetingLeaderId)
                .setRole(MeetingRole.MEETING_LEADER)
                .setState(UserState.ONLINE)
                .build());
        return attendees;
    }

    protected void publishMeetingUpdated(Meeting meeting) {
        meetingSubscriptionPublisher.publishEvent(meeting);
    }

    private Meeting updateMeeting(UUID projectId, MeetingType type, Consumer<MeetingEntity> modifier) {
        MeetingEntity meeting = repository.findTopByProjectIdAndMeetingTypeAndActive(projectId, type, true)
                .orElseThrow(() -> new MeitrexNotFoundException("No active meeting found"));

        modifier.accept(meeting);

        Meeting result = convertToDto(repository.save(meeting));

        meetingSubscriptionPublisher.publishEvent(result);

        return result;
    }

    private void updateMeetingAttendee(
            MeetingEntity meetingEntity,
            UUID userId,
            Consumer<MeetingAttendeeEmbeddable> modifier) {

        MeetingAttendeeEmbeddable attendee = meetingEntity.getAttendees().stream()
                .filter(a -> a.getUserId().equals(userId))
                .findFirst()
                .orElseGet(() -> initMeetingAttendee(meetingEntity, userId));

        modifier.accept(attendee);
    }

    private static MeetingAttendeeEmbeddable initMeetingAttendee(MeetingEntity meetingEntity, UUID userId) {
        MeetingAttendeeEmbeddable newAttendee = MeetingAttendeeEmbeddable.builder()
                .setUserId(userId)
                .setRole(MeetingRole.ATTENDEE)
                .setState(UserState.ONLINE)
                .build();

        meetingEntity.getAttendees().add(newAttendee);
        return newAttendee;
    }

}
