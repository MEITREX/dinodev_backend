package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.UserInProjectService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserInProjectController {

    private final UserInProjectService userInProjectService;

    @SchemaMapping(typeName = "GlobalUser", field = "userInProject")
    @Nullable
    public UserInProject userInProject(GlobalUser globalUser, @Argument UUID projectId) {
        return userInProjectService
                .getUserInProject(globalUser.getId(), projectId)
                .orElse(null);
    }

    @SchemaMapping(typeName = "Project", field = "users")
    public List<UserInProject> usersInProject(Project project) {
        return userInProjectService.getUsersInProject(project.getId());
    }

    @SchemaMapping(typeName = "Project", field = "currentUser")
    public UserInProject currentUserInProject(Project project) {
        return userInProjectService
                .getCurrentUserInProject(project.getId())
                .orElse(null);
    }

    @SchemaMapping(typeName = "UserInProject", field = "privateInfo")
    public PrivateUserInfo privateInfo(UserInProject userInProject) {
        return userInProjectService.getPrivateInfo(userInProject.getUserId(), userInProject.getProjectId());
    }

}
