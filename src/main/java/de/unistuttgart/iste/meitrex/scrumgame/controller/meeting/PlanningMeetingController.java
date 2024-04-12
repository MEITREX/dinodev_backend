package de.unistuttgart.iste.meitrex.scrumgame.controller.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.meeting.PlanningMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PlanningMeetingController {

    private final PlanningMeetingService planningMeetingService;

    @SchemaMapping(typeName = "ProjectMutation", field = "createPlanningMeeting")
    public PlanningMeeting createPlanningMeeting(ProjectMutation projectMutation, @Argument PlanningMeetingInput input) {
        return planningMeetingService.createPlanningMeeting(projectMutation.getId(), input);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "updatePlanningMeeting")
    public PlanningMeeting updatePlanningMeeting(ProjectMutation projectMutation, @Argument UUID id, @Argument PlanningMeetingInput input) {
        return planningMeetingService.updatePlanningMeeting(projectMutation.getId(), id, input);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "mutatePlanningMeeting")
    public PlanningMeetingMutation mutatePlanningMeeting(ProjectMutation projectMutation, @Argument UUID id) {
        return planningMeetingService.mutatePlanningMeeting(projectMutation.getId(), id);
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "changePage")
    public PlanningMeeting changePage(PlanningMeetingMutation planningMeetingMutation, @Argument PlanningMeetingPage page) {
        return planningMeetingService.changePage(planningMeetingMutation.getId(), page);
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "nextPage")
    public PlanningMeeting nextPage(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.nextPage(planningMeetingMutation.getId());
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "voteAnimal")
    public PlanningMeeting voteAnimal(PlanningMeetingMutation planningMeetingMutation, @Argument String animal, @Argument UUID userId) {
        return planningMeetingService.voteAnimal(planningMeetingMutation.getId(), animal, userId);
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "voteName")
    public PlanningMeeting voteName(PlanningMeetingMutation planningMeetingMutation, @Argument String name, @Argument UUID userId) {
        return planningMeetingService.voteName(planningMeetingMutation.getId(), name, userId);
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "voteStoryPoints")
    public PlanningMeeting voteStoryPoints(PlanningMeetingMutation planningMeetingMutation, @Argument StoryPoints storyPoints, @Argument UUID userId) {
        return planningMeetingService.voteStoryPoints(planningMeetingMutation.getId(), storyPoints, userId);
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "skipIssue")
    public PlanningMeeting skipIssue(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.skipIssue(planningMeetingMutation.getId());
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "startCountdown")
    public PlanningMeeting startCountdown(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.startCountdown(planningMeetingMutation.getId());
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "setFinalResult")
    public PlanningMeeting setFinalResult(PlanningMeetingMutation planningMeetingMutation, @Argument StoryPoints storyPoints) {
        return planningMeetingService.setFinalResult(planningMeetingMutation.getId(), storyPoints);
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "addSprintIssue")
    public PlanningMeeting addSprintIssue(PlanningMeetingMutation planningMeetingMutation, @Argument UUID issueId) {
        return planningMeetingService.addSprintIssue(planningMeetingMutation.getId(), issueId);
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "removeSprintIssue")
    public PlanningMeeting removeSprintIssue(PlanningMeetingMutation planningMeetingMutation, @Argument UUID issueId) {
        return planningMeetingService.removeSprintIssue(planningMeetingMutation.getId(), issueId);
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "setStoryPointLimit")
    public PlanningMeeting setStoryPointLimit(PlanningMeetingMutation planningMeetingMutation, @Argument int limit) {
        return planningMeetingService.setStoryPointLimit(planningMeetingMutation.getId(), limit);
    }

    @SchemaMapping(typeName = "PlanningMeetingMutation", field = "finishMeeting")
    public Sprint finishMeeting(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.finishMeeting(planningMeetingMutation.getId());
    }
}
