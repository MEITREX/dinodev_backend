package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.generated.dto.IssueMutation;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectBoard;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectMutation;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsConnector;
import de.unistuttgart.iste.meitrex.scrumgame.ims.IssueMappingConfiguration;
import de.unistuttgart.iste.meitrex.scrumgame.ims.dto.Issue;
import de.unistuttgart.iste.meitrex.scrumgame.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImsService {

    private final ImsConnector   imsConnector;
    private final ProjectService projectService;

    public List<Issue> getIssues(Project project) {
        String projectId = getImsProjectId(project);
        IssueMappingConfiguration mappingConfiguration = getIssueMappingConfiguration(project);
        return imsConnector.getIssues(projectId, mappingConfiguration);
    }

    public Optional<Issue> findIssue(Project project, String id) {
        return imsConnector.findIssue(id, getIssueMappingConfiguration(project));
    }

    public ProjectBoard getProjectBoard(Project project) {
        return ProjectBoard.builder()
                .setProjectId(project.getId())
                .setStates(project.getProjectSettings().getImsSettings().getIssueStates())
                .build();
    }

    public IssueMutation mutateIssue(ProjectMutation projectMutation, String id) {
        return new IssueMutation(projectMutation.getProjectId(), id);
    }

    public Issue changeIssueTitle(IssueMutation issueMutation, String title) {
        return imsConnector.changeIssueTitle(
                issueMutation.getIssueId(),
                title,
                getIssueMappingConfiguration(issueMutation.getProjectId()));
    }

    public Issue changeIssueDescription(IssueMutation issueMutation, String description) {
        return imsConnector.changeIssueDescription(
                issueMutation.getIssueId(),
                description,
                getIssueMappingConfiguration(issueMutation.getProjectId()));
    }

    public Issue changeIssueState(IssueMutation issueMutation, String stateName) {
        return imsConnector.changeIssueState(
                issueMutation.getIssueId(),
                stateName,
                getIssueMappingConfiguration(issueMutation.getProjectId()));
    }

    public Issue changeIssueType(IssueMutation issueMutation, String typeName) {
        return imsConnector.changeIssueType(
                issueMutation.getIssueId(),
                typeName,
                getIssueMappingConfiguration(issueMutation.getProjectId()));
    }

    public Issue assignIssue(IssueMutation issueMutation, UUID assigneeId) {
        return imsConnector.assignIssue(
                issueMutation.getIssueId(),
                assigneeId,
                getIssueMappingConfiguration(issueMutation.getProjectId()));
    }

    protected IssueMappingConfiguration getIssueMappingConfiguration(UUID projectId) {
        return getIssueMappingConfiguration(projectService.getProjectOrThrow(projectId));
    }

    private String getImsProjectId(Project project) {
        return project.getProjectSettings().getImsSettings().getImsProjectId();
    }

    private IssueMappingConfiguration getIssueMappingConfiguration(Project project) {
        return DefaultIssueMappingConfiguration.of(project.getProjectSettings().getImsSettings());
    }

}
