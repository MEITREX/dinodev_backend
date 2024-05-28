package de.unistuttgart.iste.meitrex.scrumgame.controller.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.meeting.StandupMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class StandupMeetingController {

    private final StandupMeetingService standupMeetingService;

    @SchemaMapping(typeName = "Project", field = "activeStandupMeeting")
    public StandupMeeting activeStandupMeeting(Project project) {
        return standupMeetingService.findActiveStandupMeeting(project.getId()).orElse(null);
    }

    @SchemaMapping
    public StandupMeeting createStandupMeeting(ProjectMutation projectMutation, @Argument StandupMeetingInput input) {
        return standupMeetingService.createStandupMeeting(projectMutation.getProject(), input);
    }

    @SchemaMapping
    public StandupMeetingMutation mutateStandupMeeting(ProjectMutation projectMutation) {
        return new StandupMeetingMutation(projectMutation.getProject());
    }

    @SchemaMapping
    public StandupMeeting startStandupMeeting(StandupMeetingMutation standupMeetingMutation) {
        return standupMeetingService.startStandupMeeting(standupMeetingMutation.getProject());
    }

    @SchemaMapping
    public StandupMeeting changeCurrentAttendee(StandupMeetingMutation standupMeetingMutation, @Argument UUID attendeeId) {
        return standupMeetingService.changeCurrentAttendee(standupMeetingMutation.getProject(), attendeeId);
    }

    @SchemaMapping
    public StandupMeeting finishStandupMeeting(StandupMeetingMutation standupMeetingMutation) {
        return standupMeetingService.finishStandupMeeting(standupMeetingMutation.getProject());
    }

    @SubscriptionMapping
    public Flux<StandupMeeting> standupMeeting(@Argument UUID projectId) {
        return standupMeetingService.getStandupMeetingUpdatedSubscription(projectId);
    }
}
