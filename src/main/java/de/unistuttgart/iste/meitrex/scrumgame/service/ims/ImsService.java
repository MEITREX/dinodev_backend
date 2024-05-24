package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.util.DodConfirmStateFormatter;
import de.unistuttgart.iste.meitrex.scrumgame.util.StateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImsService {

    private final ImsConnectorFactory imsConnectorFactory;
    
    public List<Issue> getIssues(Project project) {
        return imsConnectorFactory.getImsConnectorForProject(project).getIssues(project.getId());
    }

    public Optional<Issue> findIssue(Project project, String id) {
        return imsConnectorFactory.getImsConnectorForProject(project).findIssue(id);
    }

    public Optional<Issue> findIssue(UUID projectId, String issueId) {
        return imsConnectorFactory.getImsConnectorForProject(projectId).findIssue(issueId);
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
        return imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .changeIssueTitle(issueMutation.getIssueId(), title);
    }

    public Issue changeIssueDescription(IssueMutation issueMutation, String description) {
        return imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .changeIssueDescription(issueMutation.getIssueId(), description);
    }

    public Issue changeIssueState(IssueMutation issueMutation, String stateName) {
        return changeIssueState(issueMutation, getIssueStateByName(issueMutation.getProject(), stateName));
    }

    public Issue changeIssueState(IssueMutation issueMutation, IssueState state) {
        Project project = issueMutation.getProject();
        Issue issue = imsConnectorFactory.getImsConnectorForProject(project)
                .changeIssueState(issueMutation.getIssueId(), state);

        // set sprint if necessary
        if (StateUtils.isMovedOutOfSprint(issue.getState(), state)) {
            changeSprint(issueMutation, null);
        } else if (StateUtils.isMovedIntoSprint(issue.getState(), state)
                   && !Objects.equals(issue.getSprintNumber(), project.getCurrentSprintNumber())) {
            changeSprint(issueMutation, project.getCurrentSprintNumber());
        }

        return issue;
    }

    public void changeIssueState(IssueMutation issueMutation, IssueStateType issueStateType) {
        IssueState issueState = getIssueStateByType(issueStateType, issueMutation.getProject());
        changeIssueState(issueMutation, issueState);
    }

    private static IssueState getIssueStateByType(IssueStateType issueStateType, Project project) {
        return project.getProjectSettings().getImsSettings().getIssueStates().stream()
                .filter(state -> state.getType() == issueStateType)
                .findFirst()
                .orElseThrow(() -> new MeitrexNotFoundException("State not found: " + issueStateType));
    }

    private static IssueState getIssueStateByName(Project project, String stateName) {
        return project.getProjectSettings().getImsSettings()
                .getIssueStates().stream()
                .filter(issueState -> issueState.getName().equals(stateName))
                .findFirst()
                .orElseThrow(() -> new MeitrexNotFoundException("State not found: " + stateName));
    }

    public Issue changeIssueType(IssueMutation issueMutation, String typeName) {
        return imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .changeIssueType(issueMutation.getIssueId(), typeName);
    }

    public Issue assignIssue(IssueMutation issueMutation, UUID assigneeId) {
        return imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .assignIssue(issueMutation.getIssueId(), assigneeId);
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

    public List<Issue> getIssuesByIds(UUID projectId, List<String> issueIds) {
        return imsConnectorFactory.getImsConnectorForProject(projectId).findIssuesBatched(issueIds);
    }

    public Issue changeSprint(IssueMutation issueMutation, Integer sprintNumber) {
        return imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .changeSprintOfIssue(issueMutation.getIssueId(), sprintNumber);
    }

    public Issue finishIssue(IssueMutation issueMutation,
            List<DefinitionOfDoneConfirmState> dodConfirmStates,
            String doneStateName) {
        String comment = DodConfirmStateFormatter.formatDodConfirmStates(dodConfirmStates);

        imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .addCommentToIssue(issueMutation.getIssueId(), comment);

        return changeIssueState(issueMutation, doneStateName);
    }

    public void changeIssueEstimation(IssueMutation issueMutation, TShirtSizeEstimation estimation) {
        imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .changeEstimationOfIssue(issueMutation.getIssueId(), estimation);
    }

    public List<CreateEventInput> getEventsForIssue(Issue issue, OffsetDateTime since) {
        return imsConnectorFactory.getImsConnectorForProject(issue.getProjectId())
                .getEventsForIssue(issue.getId(), since);
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
