package de.unistuttgart.iste.meitrex.scrumgame.service.role;

import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.CreateProjectRoleInput;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectRole;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateProjectRoleInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for managing project roles.
 */
@Service
public class ProjectRoleService
        extends AbstractCrudService<ProjectRoleId, ProjectRoleEntity, ProjectRole> {

    public static final String ADMIN_ROLE_NAME   = "Admin";
    public static final String DEFAULT_ROLE_NAME = "Member";

    private final ProjectRoleRepository projectRoleRepository;
    private final ProjectRepository     projectRepository;

    public ProjectRoleService(
            ProjectRoleRepository projectRoleRepository,
            ProjectRepository projectRepository,
            ModelMapper modelMapper
    ) {
        super(projectRoleRepository, modelMapper, ProjectRoleEntity.class, ProjectRole.class);
        this.projectRoleRepository = projectRoleRepository;
        this.projectRepository = projectRepository;
    }

    public List<ProjectRole> getRolesOfProject(UUID projectId) {
        List<ProjectRoleEntity> roleEntities
                = projectRoleRepository.findAllByIdProjectId(projectId);

        return convertToDtos(roleEntities);
    }

    public Optional<ProjectRole> findRole(UUID projectId, String roleName) {
        return find(createId(projectId, roleName));
    }

    @PreAuthorize("@auth.hasPrivilege(@projectPrivileges.CREATE_ROLE, #projectId)")
    public ProjectRole createRole(UUID projectId, CreateProjectRoleInput input) {
        ProjectRoleId id = createId(projectId, input.getName());
        projectRoleRepository.requireNotExists(id);

        ProjectRoleEntity newRoleEntity = ProjectRoleEntity.builder()
                .id(id)
                // drawback of JPA: we have to set the project entity here
                .project(projectRepository.findByIdOrThrow(projectId))
                .projectPrivileges(input.getProjectPrivileges())
                .gamifiedName(input.getGamifiedName())
                .build();

        return create(() -> newRoleEntity);
    }

    @PreAuthorize("@auth.hasPrivilege(@projectPrivileges.UPDATE_ROLE, #projectId)")
    public ProjectRole updateRole(UUID projectId, String roleName, UpdateProjectRoleInput input) {
        return update(createId(projectId, roleName), input);
    }

    @PreAuthorize("@auth.hasPrivilege(@projectPrivileges.DELETE_ROLE, #projectId)")
    public boolean deleteRole(UUID projectId, String name) {
        // check if more logic is needed here
        return delete(createId(projectId, name));
    }

    public ProjectRoleEntity getOrCreateDefaultRole(UUID projectId) {
        return projectRoleRepository
                .findById(createId(projectId, DEFAULT_ROLE_NAME))
                .orElseGet(() -> createEntity(
                        baseRoleEntityBuilder(projectId)
                                .id(createId(projectId, DEFAULT_ROLE_NAME))
                                .projectPrivileges(List.of(ProjectPrivilege.READ_PROJECT))
                                .gamifiedName("Zoo Visitor")
                                .build()));
    }

    public ProjectRoleEntity getOrCreateAdminRole(UUID projectId) {
        return projectRoleRepository
                .findById(createId(projectId, ADMIN_ROLE_NAME))
                .orElseGet(() -> createEntity(
                        baseRoleEntityBuilder(projectId)
                                .id(createId(projectId, ADMIN_ROLE_NAME))
                                .projectPrivileges(Arrays.asList(ProjectPrivilege.values()))
                                .gamifiedName("Zoo Owner")
                                .build()));
    }

    public ProjectRoleEntity getRoleEntity(UUID projectId, String roleName) {
        return projectRoleRepository.findByIdOrThrow(createId(projectId, roleName));
    }

    private static ProjectRoleId createId(UUID projectId, String name) {
        return new ProjectRoleId(name, projectId);
    }

    private ProjectRoleEntity.ProjectRoleEntityBuilder baseRoleEntityBuilder(UUID projectId) {
        return ProjectRoleEntity.builder()
                .project(projectRepository.findByIdOrThrow(projectId));
    }

}
