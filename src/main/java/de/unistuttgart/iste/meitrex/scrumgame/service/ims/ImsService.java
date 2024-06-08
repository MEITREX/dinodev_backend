package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.util.DodConfirmStateFormatter;
import de.unistuttgart.iste.meitrex.scrumgame.util.StateUtils;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImsService {

    private final ImsConnectorFactory                     imsConnectorFactory;
    private final EventPublisher<Event, CreateEventInput> eventPublisher;

    @Cacheable(value = "issues", key = "#project.id", sync = true)
    public synchronized List<Issue> getIssues(Project project) {
        return imsConnectorFactory.getImsConnectorForProject(project).getIssues(project.getId());
    }

    @Cacheable(value = "issue", key = "#project.id + #id", sync = true)
    public synchronized Optional<Issue> findIssue(Project project, String id) {
        return imsConnectorFactory.getImsConnectorForProject(project).findIssue(id);
    }

    @Cacheable(value = "issue", key = "#projectId + #issueId", sync = true)
    public synchronized Optional<Issue> findIssue(UUID projectId, String issueId) {
        return imsConnectorFactory.getImsConnectorForProject(projectId).findIssue(issueId);
    }

    public List<Issue> getIssuesByIds(UUID projectId, List<String> issueIds) {
        return imsConnectorFactory.getImsConnectorForProject(projectId).findIssuesBatched(issueIds);
    }

    public ProjectBoard getProjectBoard(Project project) {
        return new ProjectBoard(project);
    }

    public List<IssueStateInBoard> getIssueStates(ProjectBoard board) {
        return convertIssueStates(
                board.getProject().getProjectSettings().getImsSettings().getIssueStates(), board);
    }

    @Caching(evict = {
            @CacheEvict(value = "issues", key = "#project.id"),
            @CacheEvict(value = "issue", key = "#project.id + #id")
    })
    public IssueMutation mutateIssue(Project project, String id) {
        return new IssueMutation(project, id);
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

        if (issue == null) {
            return null; // TODO make return optional
        }

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

    public Issue changeSprint(IssueMutation issueMutation, Integer sprintNumber) {
        return imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .changeSprintOfIssue(issueMutation.getIssueId(), sprintNumber);
    }

    public Issue finishIssue(IssueMutation issueMutation,
            List<DefinitionOfDoneConfirmState> dodConfirmStates,
            String doneStateName) {
        String comment = DodConfirmStateFormatter.formatDodConfirmStates(dodConfirmStates);

        imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .addCommentToIssue(issueMutation.getIssueId(), comment, null);

        Issue result = changeIssueState(issueMutation, doneStateName);

        syncEvents(result, OffsetDateTime.now().minusMinutes(5));

        return result;
    }

    public void changeIssueEstimation(IssueMutation issueMutation, TShirtSizeEstimation estimation) {
        imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .changeEstimationOfIssue(issueMutation.getIssueId(), estimation);
    }

    public List<CreateEventInput> getEventsForIssue(Issue issue, OffsetDateTime since) {
        return imsConnectorFactory.getImsConnectorForProject(issue.getProjectId())
                .getEventsForIssue(issue.getId(), since);
    }

    @CachePut(value = "issue", key = "#projectMutation.getProject().id + #result.getId()")
    @CacheEvict(value = "issues", key = "#projectMutation.getProject().id")
    public Issue createIssue(ProjectMutation projectMutation, CreateIssueInput input) {
        return imsConnectorFactory.getImsConnectorForProject(projectMutation.getProject())
                .createIssue(input);
    }

    public Issue commentOnIssue(IssueMutation issueMutation, String comment, @Nullable String parentId) {
        return imsConnectorFactory.getImsConnectorForProject(issueMutation.getProject())
                .addCommentToIssue(issueMutation.getIssueId(), comment, parentId);
    }

    @Caching(evict = {
            @CacheEvict(value = "issues", key = "#issue.projectId"),
            @CacheEvict(value = "issue", key = "#issue.projectId + #issue.id")
    })
    public void syncEvents(Issue issue, OffsetDateTime since) {
        getEventsForIssue(issue, since).forEach(eventPublisher::publishEvent);
    }

    public List<CreateEventInput> getEventsForProject(Project project, OffsetDateTime lastGlobalEventSync) {
        return imsConnectorFactory.getImsConnectorForProject(project)
                .getEventsForProject(project.getId(), lastGlobalEventSync);
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
