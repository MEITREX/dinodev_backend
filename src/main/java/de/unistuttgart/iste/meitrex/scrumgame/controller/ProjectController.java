package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @QueryMapping
    public List<Project> projects() {
        return projectService.getAllProjects();
    }

    @QueryMapping
    public Project project(@Argument UUID id) {
        return projectService.getProject(id);
    }

    @MutationMapping
    public Project createProject(@Argument CreateProjectInput input) {
        return projectService.createProject(input);
    }

    @MutationMapping
    public Project updateProject(@Argument UUID id, @Argument UpdateProjectInput input) {
        return projectService.updateProject(id, input);
    }

    @MutationMapping
    public Project deleteProject(@Argument UUID id) {
        return projectService.deleteProject(id);
    }

    @MutationMapping
    public ProjectMutation mutateProject(@Argument UUID id) {
        return projectService.mutateProject(id);
    }

    @SchemaMapping(typeName = "UserInProject", field = "project")
    public Project project(UserInProject userInProject) {
        return projectService.getProject(userInProject.getProjectId());
    }
}
