package de.unistuttgart.iste.meitrex.scrumgame.controller.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.meeting.PlanningMeetingService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.*;


@Slf4j
@Controller
@RequiredArgsConstructor
public class PlanningMeetingController {

    private final PlanningMeetingService planningMeetingService;

    @SchemaMapping
    public PlanningMeeting createPlanningMeeting(ProjectMutation projectMutation,
            @Argument PlanningMeetingInput input) {
        return planningMeetingService.createPlanningMeeting(projectMutation.getProject(), input);
    }

    @SchemaMapping(typeName = "Project", field = "activePlanningMeeting")
    @Nullable
    public PlanningMeeting activePlanningMeeting(Project project) {
        return planningMeetingService.findActivePlanningMeeting(project.getId()).orElse(null);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "mutatePlanningMeeting")
    public PlanningMeetingMutation mutatePlanningMeeting(ProjectMutation projectMutation) {
        return planningMeetingService.mutatePlanningMeeting(projectMutation.getProject());
    }

    @SchemaMapping
    public EstimationStats estimationStats(IssueEstimation issueEstimation) {
        return planningMeetingService.calculateEstimationStats(issueEstimation).orElse(null);
    }

    /* PlanningMeetingMutation */

    @SchemaMapping
    public PlanningMeeting changePage(PlanningMeetingMutation planningMeetingMutation,
            @Argument PlanningMeetingPage page) {
        return planningMeetingService.changePage(planningMeetingMutation.getProject().getId(), page);
    }

    @SchemaMapping
    public PlanningMeeting voteAnimal(PlanningMeetingMutation planningMeetingMutation, @Argument Animal animal) {
        return planningMeetingService.voteAnimal(planningMeetingMutation.getProject().getId(), animal);
    }

    @SchemaMapping
    public PlanningMeeting endAnimalVoting(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.endAnimalVoting(planningMeetingMutation.getProject().getId());
    }

    @SchemaMapping
    public PlanningMeeting addName(PlanningMeetingMutation planningMeetingMutation, @Argument String name) {
        return planningMeetingService.addName(planningMeetingMutation.getProject().getId(), name);
    }

    @SchemaMapping
    public PlanningMeeting voteName(PlanningMeetingMutation planningMeetingMutation, @Argument String name) {
        return planningMeetingService.voteName(planningMeetingMutation.getProject().getId(), name);
    }

    @SchemaMapping
    public PlanningMeeting endNameVoting(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.endNameVoting(planningMeetingMutation.getProject().getId());
    }

    @SchemaMapping
    public PlanningMeeting voteEstimation(PlanningMeetingMutation planningMeetingMutation,
            @Argument TShirtSizeEstimation estimation) {
        return planningMeetingService.voteEstimation(planningMeetingMutation.getProject().getId(), estimation);
    }

    @SchemaMapping
    public PlanningMeeting restartEstimation(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.restartEstimation(planningMeetingMutation.getProject().getId());
    }

    @SchemaMapping
    public PlanningMeeting nextIssue(
            PlanningMeetingMutation planningMeetingMutation,
            @Argument @Nullable String issueId) {
        return planningMeetingService.nextIssue(planningMeetingMutation.getProject().getId(), issueId);
    }

    @SchemaMapping
    public PlanningMeeting startCountdown(PlanningMeetingMutation planningMeetingMutation, @Argument Integer seconds) {
        return planningMeetingService.startCountdown(planningMeetingMutation.getProject().getId(), seconds);
    }

    @SchemaMapping
    public PlanningMeeting endEstimation(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.endEstimation(planningMeetingMutation.getProject().getId());
    }

    @SchemaMapping
    public PlanningMeeting setFinalResult(PlanningMeetingMutation planningMeetingMutation,
            @Argument TShirtSizeEstimation estimation) {
        return planningMeetingService.setFinalResult(planningMeetingMutation.getProject(), estimation);
    }

    @SchemaMapping
    public PlanningMeeting addSprintIssue(PlanningMeetingMutation planningMeetingMutation, @Argument String issueId) {
        return planningMeetingService.addSprintIssue(planningMeetingMutation.getProject().getId(), issueId);
    }

    @SchemaMapping
    public PlanningMeeting removeSprintIssue(PlanningMeetingMutation planningMeetingMutation,
            @Argument String issueId) {
        return planningMeetingService.removeSprintIssue(planningMeetingMutation.getProject().getId(), issueId);
    }

    @SchemaMapping
    public PlanningMeeting finishSprintGoalVoting(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.finishSprintGoalVoting(planningMeetingMutation.getProject().getId());
    }

    @SchemaMapping
    public Sprint finishMeeting(PlanningMeetingMutation planningMeetingMutation) {
        return planningMeetingService.finishMeeting(planningMeetingMutation.getProject().getId());
    }

    @SubscriptionMapping
    public Flux<PlanningMeeting> planningMeeting(@Argument UUID projectId) {
        log.info("Subscribing to planning meeting updates for project {}", projectId);
        return planningMeetingService.getPlanningMeetingUpdatedSubscription(projectId);
    }
}
