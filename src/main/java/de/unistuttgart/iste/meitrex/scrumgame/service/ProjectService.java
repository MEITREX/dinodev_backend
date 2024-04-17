package de.unistuttgart.iste.meitrex.scrumgame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ObjectMapper objectMapper;

    public List<Project> getAllProjects() {
        List<ProjectEntity> projectEntities = projectRepository.findAll();

        return convertToDtos(projectEntities);
    }

    public Optional<Project> getProject(UUID id) {
        return projectRepository.findById(id)
                .map(this::convertToDto);
    }

    public Project getProjectOrThrow(UUID projectId) {
        return convertToDto(projectRepository.findByIdOrThrow(projectId));
    }

    // @PreAuthorize("@auth.hasPrivilege(@globalPrivileges.CREATE_PROJECT)")
    public Project createProject(CreateProjectInput input) {
        ProjectEntity newProjectEntity = ProjectEntity.builder()
                .name(input.getName())
                .description(input.getDescription())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .build();

        ProjectEntity savedProjectEntity = projectRepository.save(newProjectEntity);

        return convertToDto(savedProjectEntity);
    }

    @PreAuthorize("@auth.hasPrivilege(@projectPrivileges.UPDATE_PROJECT, #projectId)")
    public Project updateProject(UUID projectId, UpdateProjectInput input) {
        ProjectEntity projectEntity = projectRepository.findByIdOrThrow(projectId);

        projectEntity.setName(input.getName());
        projectEntity.setDescription(input.getDescription());
        projectEntity.setStartDate(input.getStartDate());
        projectEntity.setEndDate(input.getEndDate());

        ProjectEntity savedProjectEntity = projectRepository.save(projectEntity);

        return convertToDto(savedProjectEntity);
    }

    @PreAuthorize("@auth.hasPrivilege(@projectPrivileges.DELETE_PROJECT, #projectId)")
    public Project deleteProject(UUID projectId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Note: Authorization is checked in the specific sub-mutations
    public ProjectMutation mutateProject(UUID projectId) {
        return new ProjectMutation(projectId);
    }

    private Project convertToDto(ProjectEntity projectEntity) {
        return objectMapper.convertValue(projectEntity, Project.class);
    }

    private List<Project> convertToDtos(List<ProjectEntity> projectEntities) {
        return projectEntities.stream()
                .map(this::convertToDto)
                .toList();
    }
}
