package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.UserRoleInProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserRoleInProjectController {

    private final UserRoleInProjectService userRoleInProjectService;

    @SchemaMapping(typeName = "Project", field = "roles")
    public List<UserRoleInProject> roles(Project project) {
        return userRoleInProjectService.getRolesOfProject(project.getId());
    }

    @SchemaMapping(typeName = "Project", field = "role")
    public UserRoleInProject role(Project project, @Argument String name) {
        return userRoleInProjectService.getRole(project.getId(), name);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "createRole")
    public UserRoleInProject createRole(ProjectMutation projectMutation, @Argument CreateRoleInput input) {
        return userRoleInProjectService.createRole(projectMutation.getId(), input);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "updateRole")
    public UserRoleInProject updateRole(ProjectMutation projectMutation,
                                        @Argument String name,
                                        @Argument UpdateRoleInput input) {
        return userRoleInProjectService.updateRole(projectMutation.getId(), name, input);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "deleteRole")
    public boolean deleteRole(ProjectMutation projectMutation, @Argument String name) {
        return userRoleInProjectService.deleteRole(projectMutation.getId(), name);
    }
}
