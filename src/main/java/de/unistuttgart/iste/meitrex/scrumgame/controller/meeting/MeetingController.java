package de.unistuttgart.iste.meitrex.scrumgame.controller.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.Meeting;
import de.unistuttgart.iste.meitrex.generated.dto.MeetingType;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectMutation;
import de.unistuttgart.iste.meitrex.scrumgame.service.meeting.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @SchemaMapping(typeName = "ProjectMutation", field = "joinMeeting")
    public Meeting joinMeeting(ProjectMutation projectMutation, @Argument MeetingType type) {
        return meetingService.joinMeeting(projectMutation.getProject().getId(), type);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "leaveMeeting")
    public Meeting leaveMeeting(ProjectMutation projectMutation, @Argument MeetingType type) {
        return meetingService.leaveMeeting(projectMutation.getProject().getId(), type);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "pingMeeting")
    public Meeting pingMeeting(ProjectMutation projectMutation, @Argument MeetingType type) {
        return meetingService.pingMeeting(projectMutation.getProject().getId(), type);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "promoteToMeetingLeader")
    public Meeting promoteToMeetingLeader(ProjectMutation projectMutation,
            @Argument MeetingType type,
            @Argument UUID userId) {
        return meetingService.promoteToMeetingLeader(projectMutation.getProject().getId(), type, userId);
    }

    @SubscriptionMapping
    public Flux<Meeting> meeting(@Argument UUID projectId, @Argument MeetingType meetingType) {
        return meetingService.getMeetingUpdates(projectId, meetingType, Meeting.class);
    }

//    @SubscriptionMapping
//    public Flux<Meeting> meetingStarted(@Argument UUID projectId) {
//        return meetingEventHandler.subscribeToMeetingStarted(projectId);
//    }
//
//    @SubscriptionMapping
//    public Flux<Meeting> meetingFinished(@Argument UUID projectId) {
//        return meetingEventHandler.subscribeToMeetingFinished(projectId);
//    }
//
//    @SubscriptionMapping
//    public Flux<List<MeetingAttendee>> meetingAttendeesChanged(@Argument UUID projectId, @Argument MeetingType meetingType) {
//        return meetingEventHandler.subscribeToMeetingAttendeesChanged(projectId, meetingType);
//    }
}