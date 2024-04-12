package de.unistuttgart.iste.meitrex.scrumgame.controller.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.meeting.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @SchemaMapping(typeName = "Project", field = "meetings")
    public List<Meeting> meetings(Project project, @Argument Boolean active) {
        return meetingService.getAllMeetings(project.getId(), active);
    }

    @SchemaMapping(typeName = "Project", field = "meeting")
    public Meeting meeting(Project project, @Argument UUID id) {
        return meetingService.getMeeting(project.getId(), id);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "startMeeting")
    public Meeting startMeeting(ProjectMutation projectMutation, @Argument UUID id) {
        return meetingService.startMeeting(projectMutation.getId(), id);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "finishMeeting")
    public Meeting finishMeeting(ProjectMutation projectMutation, @Argument UUID id) {
        return meetingService.finishMeeting(projectMutation.getId(), id);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "joinMeeting")
    public Meeting joinMeeting(ProjectMutation projectMutation, @Argument UUID id) {
        return meetingService.joinMeeting(projectMutation.getId(), id);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "leaveMeeting")
    public Meeting leaveMeeting(ProjectMutation projectMutation, @Argument UUID id) {
        return meetingService.leaveMeeting(projectMutation.getId(), id);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "pingMeeting")
    public Meeting pingMeeting(ProjectMutation projectMutation, @Argument UUID id) {
        return meetingService.pingMeeting(projectMutation.getId(), id);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "promoteToMeetingLeader")
    public Meeting promoteToMeetingLeader(ProjectMutation projectMutation, @Argument UUID meetingId, @Argument UUID userId) {
        return meetingService.promoteToMeetingLeader(projectMutation.getId(), meetingId, userId);
    }

    @SubscriptionMapping
    public Flux<Meeting> meetingStarted(@Argument UUID projectId) {
        return meetingService.subscribeToMeetingStarted(projectId);
    }

    @SubscriptionMapping
    public Flux<Meeting> meetingFinished(@Argument UUID projectId) {
        return meetingService.subscribeToMeetingFinished(projectId);
    }
}