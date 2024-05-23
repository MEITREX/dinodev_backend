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
        return updateMeeting(projectId, type, entity -> {
            UUID currentUserId = authService.getCurrentUserId();
            updateMeetingAttendee(entity, currentUserId, attendee -> attendee.setState(UserState.OFFLINE));
        });
    }

    public Flux<Meeting> getMeetingUpdates(UUID projectId, MeetingType type) {
        return meetingSubscriptionPublisher.getEventStream()
                .filter(meeting -> meeting.getProjectId().equals(projectId) && meeting.getMeetingType() == type);
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
