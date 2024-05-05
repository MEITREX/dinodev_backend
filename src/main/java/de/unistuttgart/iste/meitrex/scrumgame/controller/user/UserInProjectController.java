package de.unistuttgart.iste.meitrex.scrumgame.controller.user;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.UserInProjectService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserInProjectController {

    private final UserInProjectService userInProjectService;

    @SchemaMapping
    @Nullable
    public UserInProject userInProject(GlobalUser globalUser, @Argument UUID projectId) {
        return userInProjectService
                .findUserInProject(globalUser.getId(), projectId)
                .orElse(null);
    }

    @SchemaMapping
    public List<UserInProject> userInProjects(GlobalUser globalUser) {
        return userInProjectService.getUserInProjectsByUserId(globalUser.getId());
    }

    @SchemaMapping
    public List<UserInProject> users(Project project) {
        return userInProjectService.getUsersInProjectByProjectId(project.getId());
    }

    @SchemaMapping
    public UserInProject currentUser(Project project) {
        return userInProjectService
                .findCurrentUserInProject(project.getId())
                .orElse(null);
    }

    @SchemaMapping
    public PrivateUserInfo privateInfo(UserInProject userInProject) {
        return userInProjectService.getPrivateInfo(userInProject.getUserId(), userInProject.getProjectId());
    }

    @SchemaMapping
    public List<UserInProject> assignees(Issue issue) {
        return userInProjectService.getUsers(issue.getProjectId(), issue.getAssigneeIds());
    }

    @MutationMapping
    public UserInProject joinProject(@Argument UUID projectId) {
        return userInProjectService.joinProject(projectId);
    }

}
