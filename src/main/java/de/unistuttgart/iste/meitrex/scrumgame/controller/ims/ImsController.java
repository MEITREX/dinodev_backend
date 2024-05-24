package de.unistuttgart.iste.meitrex.scrumgame.controller.ims;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ImsController {

    private final ImsService imsService;

    /* Schema mappings */

    @SchemaMapping
    public List<Issue> issues(Project project) {
        return imsService.getIssues(project);
    }

    @SchemaMapping
    public Issue issue(Project project, @Argument String id) {
        return imsService.findIssue(project, id).orElse(null);
    }


    /* ProjectBoard mappings */

    @SchemaMapping
    public ProjectBoard projectBoard(Project project) {
        return imsService.getProjectBoard(project);
    }

    @SchemaMapping
    public List<IssueStateInBoard> states(ProjectBoard board) {
        return imsService.getIssueStates(board);
    }

    @BatchMapping
    public Map<IssueStateInBoard, List<Issue>> issues(List<IssueStateInBoard> states) {
        return imsService.getIssuesByStates(states);
    }

    @BatchMapping(typeName = "Sprint", field = "issues")
    public Map<Sprint, List<Issue>> issuesOfSprints(List<Sprint> sprints) {
        return imsService.getIssuesBySprints(sprints);
    }

    @SchemaMapping(typeName = "IssueEstimation", field = "issue")
    public Issue issuesOfEstimations(IssueEstimation estimation) {
        return imsService
                .findIssue(estimation.getPlanningMeeting().getProjectId(), estimation.getIssueId())
                .orElse(null);
    }

    @SchemaMapping
    public List<Issue> sprintIssues(SprintGoalVoting sprintGoalVoting) {
        return imsService.getIssuesByIds(
                sprintGoalVoting.getPlanningMeeting().getProjectId(),
                sprintGoalVoting.getSprintIssueIds());
    }

    @SchemaMapping
    public List<Issue> nonSprintIssues(SprintGoalVoting sprintGoalVoting) {
        return imsService.getIssuesByIds(
                sprintGoalVoting.getPlanningMeeting().getProjectId(),
                sprintGoalVoting.getNonSprintIssueIds());
    }

    /* IssueMutation mappings */

    @SchemaMapping
    public IssueMutation mutateIssue(ProjectMutation projectMutation, @Argument String id) {
        return imsService.mutateIssue(projectMutation, id);
    }

    @SchemaMapping
    public Issue changeIssueTitle(IssueMutation issueMutation, @Argument String title) {
        return imsService.changeIssueTitle(issueMutation, title);
    }

    @SchemaMapping
    public Issue changeIssueDescription(IssueMutation issueMutation, @Argument String description) {
        return imsService.changeIssueDescription(issueMutation, description);
    }

    @SchemaMapping
    public Issue changeIssueState(IssueMutation issueMutation, @Argument String stateName) {
        return imsService.changeIssueState(issueMutation, stateName);
    }

    @SchemaMapping
    public Issue changeIssueType(IssueMutation issueMutation, @Argument String typeName) {
        return imsService.changeIssueType(issueMutation, typeName);
    }

    @SchemaMapping
    public Issue assignIssue(IssueMutation issueMutation, @Argument UUID assigneeId) {
        return imsService.assignIssue(issueMutation, assigneeId);
    }

    @SchemaMapping
    public Issue finishIssue(IssueMutation issueMutation,
            @Argument List<DefinitionOfDoneConfirmState> dodConfirmStates,
            @Argument String doneStateName) {
        return imsService.finishIssue(issueMutation, dodConfirmStates, doneStateName);
    }

    @SchemaMapping
    public Issue changeSprint(IssueMutation issueMutation, @Argument Integer sprintNumber) {
        return imsService.changeSprint(issueMutation, sprintNumber);
    }

}
