package de.unistuttgart.iste.meitrex.scrumgame.controller.role;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.role.ProjectRoleService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectRoleController {

    private final ProjectRoleService userRoleInProjectService;

    @SchemaMapping(typeName = "Project", field = "roles")
    public List<ProjectRole> roles(Project project) {
        return userRoleInProjectService.getRolesOfProject(project.getId());
    }

    @SchemaMapping(typeName = "Project", field = "role")
    @Nullable
    public ProjectRole role(Project project, @Argument String name) {
        return userRoleInProjectService
                .findRole(project.getId(), name)
                .orElse(null);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "createRole")
    public ProjectRole createRole(ProjectMutation projectMutation,
            @Argument CreateProjectRoleInput input) {
        return userRoleInProjectService.createRole(projectMutation.getProjectId(), input);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "updateRole")
    public ProjectRole updateRole(ProjectMutation projectMutation,
                                        @Argument String name,
            @Argument UpdateProjectRoleInput input) {
        return userRoleInProjectService.updateRole(projectMutation.getProjectId(), name, input);
    }

    @SchemaMapping(typeName = "ProjectMutation", field = "deleteRole")
    public boolean deleteRole(ProjectMutation projectMutation, @Argument String name) {
        return userRoleInProjectService.deleteRole(projectMutation.getProjectId(), name);
    }
}
