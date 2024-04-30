package de.unistuttgart.iste.meitrex.scrumgame.controller.ims;

import de.unistuttgart.iste.meitrex.generated.dto.IssueMutation;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectBoard;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectMutation;
import de.unistuttgart.iste.meitrex.scrumgame.ims.dto.Issue;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
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

    @SchemaMapping
    public ProjectBoard projectBoard(Project project) {
        return imsService.getProjectBoard(project);
    }

    /* ProjectBoard mappings */

    @SchemaMapping
    public IssueMutation mutateIssue(ProjectMutation projectMutation, @Argument String id) {
        return imsService.mutateIssue(projectMutation, id);
    }

    /* IssueMutation mappings */

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

}
