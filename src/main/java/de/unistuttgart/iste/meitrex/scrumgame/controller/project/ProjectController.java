package de.unistuttgart.iste.meitrex.scrumgame.controller.project;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.project.ProjectService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /* Query Mappings */

    @QueryMapping
    public List<Project> projects() {
        return projectService.getAllProjects();
    }

    @QueryMapping
    @Nullable
    public Project project(@Argument UUID id) {
        return projectService.findProject(id).orElse(null);
    }

    /* Schema Mappings */

    @SchemaMapping
    public Project project(UserInProject userInProject) {
        return projectService.getProjectOrThrow(userInProject.getProjectId());
    }

    /* Mutation Mappings */

    @MutationMapping
    public Project createProject(@Argument CreateProjectInput input) {
        return projectService.createProject(input);
    }

    @MutationMapping
    public Project updateProject(@Argument UUID id, @Argument UpdateProjectInput input) {
        return projectService.updateProject(id, input);
    }

    @MutationMapping
    public boolean deleteProject(@Argument UUID id) {
        return projectService.deleteProject(id);
    }

    @MutationMapping
    public ProjectMutation mutateProject(@Argument UUID id) {
        return projectService.mutateProject(id);
    }
}
