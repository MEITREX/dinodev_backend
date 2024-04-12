package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.UserRoleInProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserRoleInProjectController {

    private final UserRoleInProjectService userRoleInProjectService;

    @SchemaMapping(typeName = "Project", field = "roles")
    public List<UserRoleInProject> roles(Project project) {
        return userRoleInProjectService.getAllRoles(project.getId());
    }

    @SchemaMapping(typeName = "Project", field = "role")
    public UserRoleInProject role(Project project, @Argument UUID id) {
        return userRoleInProjectService.getRole(project.getId(), id);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "createRole")
    public UserRoleInProject createRole(ProjectMutation projectMutation, @Argument CreateRoleInput input) {
        return userRoleInProjectService.createRole(projectMutation.getId(), input);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "updateRole")
    public UserRoleInProject updateRole(ProjectMutation projectMutation,
                                        @Argument UUID id,
                                        @Argument UpdateRoleInput input) {
        return userRoleInProjectService.updateRole(projectMutation.getId(), id, input);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "deleteRole")
    public boolean deleteRole(ProjectMutation projectMutation, @Argument UUID id) {
        return userRoleInProjectService.deleteRole(projectMutation.getId(), id);
    }
}
