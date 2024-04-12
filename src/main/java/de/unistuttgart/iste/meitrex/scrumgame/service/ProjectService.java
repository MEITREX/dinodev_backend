package de.unistuttgart.iste.meitrex.scrumgame.service;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProjectService {

    // @PreAuthorize("@auth.hasGlobalPrivilege('CREATE_PROJECT')")
    public List<Project> getAllProjects() {
        return IntStream.range(0, 100)
                .mapToObj(i -> Project.builder()
                        .setId(UUID.randomUUID())
                        .setName("Project " + i)
                        .setDescription("Description " + i)
                        .setStartDate(LocalDate.of(2021, 1, 1)
                                .atStartOfDay()
                                .atOffset(ZoneOffset.ofHours(2)))
                        .setEndDate(LocalDate.of(2021, 12, 31)
                                .atStartOfDay()
                                .atOffset(ZoneOffset.ofHours(2)))
                        .build())
                .toList();
    }

    public Project getProject(UUID id) {
        return Project.builder()
                .setId(id)
                .setDescription("Description")
                .setStartDate(LocalDate.of(2021, 1, 1)
                        .atStartOfDay()
                        .atOffset(ZoneOffset.ofHours(2)))
                .setEndDate(LocalDate.of(2021, 12, 31)
                        .atStartOfDay()
                        .atOffset(ZoneOffset.ofHours(2)))
                .setName("Project")
                .build();
    }


    @PreAuthorize("@auth.hasGlobalPrivilege('CREATE_PROJECT')")
    public Project createProject(CreateProjectInput input) {
        return Project.builder().setId(UUID.randomUUID()).setName(input.getName()).build();
    }

    @PreAuthorize("@auth.hasProjectPrivilege('UPDATE_PROJECT', #projectId)")
    public Project updateProject(UUID projectId, UpdateProjectInput input) {
        return getProject(UUID.randomUUID());
    }

    @PreAuthorize("@auth.hasProjectPrivilege('DELETE_PROJECT', #projectId)")
    public Project deleteProject(UUID projectId) {
        return getProject(projectId);
    }

    // Note: Authorization is checked in the specific sub-mutations
    public ProjectMutation mutateProject(UUID projectId) {
        return ProjectMutation.builder().setId(UUID.randomUUID()).build();
    }
}
