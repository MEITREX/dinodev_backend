package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsConnector;
import de.unistuttgart.iste.meitrex.scrumgame.ims.IssueMappingConfiguration;
import de.unistuttgart.iste.meitrex.scrumgame.util.DodConfirmStateFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImsService {

    private final ImsConnector<IssueMappingConfiguration> imsConnector;

    public List<Issue> getIssues(Project project) {
        IssueMappingConfiguration mappingConfiguration = DefaultIssueMappingConfiguration.of(project);
        return imsConnector.getIssues(project.getId(), mappingConfiguration);
    }

    public Optional<Issue> findIssue(Project project, String id) {
        return imsConnector.findIssue(id, DefaultIssueMappingConfiguration.of(project));
    }

    public ProjectBoard getProjectBoard(Project project) {
        return new ProjectBoard(project);
    }

    public List<IssueStateInBoard> getIssueStates(ProjectBoard board) {
        return convertIssueStates(
                board.getProject().getProjectSettings().getImsSettings().getIssueStates(), board);
    }

    public IssueMutation mutateIssue(ProjectMutation projectMutation, String id) {
        return new IssueMutation(projectMutation.getProject(), id);
    }

    public Issue changeIssueTitle(IssueMutation issueMutation, String title) {
        return imsConnector.changeIssueTitle(
                issueMutation.getIssueId(),
                title,
                getIssueMappingConfiguration(issueMutation.getProject()));
    }

    public Issue changeIssueDescription(IssueMutation issueMutation, String description) {
        return imsConnector.changeIssueDescription(
                issueMutation.getIssueId(),
                description,
                getIssueMappingConfiguration(issueMutation.getProject()));
    }

    public Issue changeIssueState(IssueMutation issueMutation, String stateName) {
        return changeIssueState(issueMutation, getIssueStateByName(issueMutation.getProject(), stateName));
    }

    public Issue changeIssueState(IssueMutation issueMutation, IssueState state) {
        Project project = issueMutation.getProject();
        Issue issue = imsConnector.changeIssueState(
                issueMutation.getIssueId(),
                state,
                getIssueMappingConfiguration(project));

        // set sprint if necessary
        if (isMovedOutOfSprint(state)) {
            changeSprint(issueMutation, null);
        } else if (isMovedIntoSprint(state, issue, project)) {
            changeSprint(issueMutation, project.getCurrentSprintNumber());
        }

        return issue;
    }

    private static boolean isMovedIntoSprint(IssueState state, Issue issue, Project project) {
        return state.getType() != IssueStateType.DONE
               && !Objects.equals(issue.getSprintNumber(), project.getCurrentSprintNumber());
    }

    private static boolean isMovedOutOfSprint(IssueState state) {
        return state.getType() == IssueStateType.BACKLOG;
    }

    private static IssueState getIssueStateByName(Project project, String stateName) {
        return project.getProjectSettings().getImsSettings()
                .getIssueStates().stream()
                .filter(issueState -> issueState.getName().equals(stateName))
                .findFirst()
                .orElseThrow(() -> new MeitrexNotFoundException("State not found: " + stateName));
    }

    public Issue changeIssueType(IssueMutation issueMutation, String typeName) {
        return imsConnector.changeIssueType(
                issueMutation.getIssueId(),
                typeName,
                getIssueMappingConfiguration(issueMutation.getProject()));
    }

    public Issue assignIssue(IssueMutation issueMutation, UUID assigneeId) {
        return imsConnector.assignIssue(
                issueMutation.getIssueId(),
                assigneeId,
                getIssueMappingConfiguration(issueMutation.getProject()));
    }

    public Map<IssueStateInBoard, List<Issue>> getIssuesByStates(List<IssueStateInBoard> states) {
        Map<IssueStateInBoard, List<Issue>> resultMap = new HashMap<>();

        for (IssueStateInBoard state : states) {
            List<Issue> issues = getIssues(state.getProjectBoard().getProject());
            List<Issue> stateIssues = issues.stream()
                    .filter(issue -> Objects.equals(issue.getState().getImsStateId(), state.getState().getImsStateId()))
                    .collect(Collectors.toList());
            resultMap.put(state, stateIssues);
        }

        return resultMap;
    }

    public Map<Sprint, List<Issue>> getIssuesBySprints(List<Sprint> sprints) {
        Map<Sprint, List<Issue>> resultMap = new HashMap<>();

        for (Sprint sprint : sprints) {
            List<Issue> issues = getIssues(sprint.getProject());
            List<Issue> sprintIssues = issues.stream()
                    .filter(issue -> Objects.equals(issue.getSprintNumber(), sprint.getNumber()))
                    .collect(Collectors.toList());
            resultMap.put(sprint, sprintIssues);
        }

        return resultMap;
    }

    public Issue changeSprint(IssueMutation issueMutation, Integer sprintNumber) {
        return imsConnector.changeSprintOfIssue(
                issueMutation.getIssueId(),
                sprintNumber,
                getIssueMappingConfiguration(issueMutation.getProject()));
    }

    public Issue finishIssue(IssueMutation issueMutation,
            List<DefinitionOfDoneConfirmState> dodConfirmStates,
            String doneStateName) {
        log.info("Finishing issue {} with DOD confirm states: {}", issueMutation.getIssueId(), dodConfirmStates);
        String comment = DodConfirmStateFormatter.formatDodConfirmStates(dodConfirmStates);

        imsConnector.addCommentToIssue(
                issueMutation.getIssueId(),
                comment,
                getIssueMappingConfiguration(issueMutation.getProject()));

        return changeIssueState(issueMutation, doneStateName);
    }

    protected IssueMappingConfiguration getIssueMappingConfiguration(Project project) {
        return DefaultIssueMappingConfiguration.of(project);
    }

    private IssueStateInBoard toIssueStateInBoard(IssueState issueState, ProjectBoard board) {
        return new IssueStateInBoard(issueState, board);
    }

    private List<IssueStateInBoard> convertIssueStates(List<IssueState> issueStates, ProjectBoard board) {
        return issueStates.stream()
                .map(issueState -> toIssueStateInBoard(issueState, board))
                .collect(Collectors.toList());
    }

}
