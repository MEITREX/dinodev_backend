package de.unistuttgart.iste.meitrex.scrumgame.service.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.Meeting;
import de.unistuttgart.iste.meitrex.generated.dto.PlanningMeeting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeetingService {
    public List<Meeting> getAllMeetings(UUID projectId, Boolean active) {
        return List.of(getMeeting(projectId, UUID.randomUUID()));
    }


    public Meeting getMeeting(UUID projectId, UUID id) {
        return PlanningMeeting.builder().setId(id).setDescription("Meeting").build();
    }

    public Meeting startMeeting(UUID projectId, UUID id) {
        return getMeeting(projectId, id);
    }

    public Meeting finishMeeting(UUID projectId, UUID id) {
        return getMeeting(projectId, id);
    }

    public Meeting joinMeeting(UUID projectId, UUID id) {
        return getMeeting(projectId, id);
    }

    public Meeting promoteToMeetingLeader(UUID projectId, UUID meetingId, UUID userId) {
        return getMeeting(projectId, meetingId);
    }

    public Meeting pingMeeting(UUID projectId, UUID id) {
        return getMeeting(projectId, id);
    }

    public Meeting leaveMeeting(UUID projectId, UUID id) {
        return getMeeting(projectId, id);
    }

    public Flux<Meeting> subscribeToMeetingStarted(UUID projectId) {
        return Flux.just(getMeeting(projectId, UUID.randomUUID()));
    }

    public Flux<Meeting> subscribeToMeetingFinished(UUID projectId) {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
        Flux<Meeting> meetings = Flux.just(
                getMeeting(projectId, UUID.randomUUID()),
                getMeeting(projectId, UUID.randomUUID()),
                getMeeting(projectId, UUID.randomUUID())
        );

        return Flux.zip(interval, meetings).map(Tuple2::getT2);
    }
}
