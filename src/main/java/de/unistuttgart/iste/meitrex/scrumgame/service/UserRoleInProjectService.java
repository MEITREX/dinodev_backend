package de.unistuttgart.iste.meitrex.scrumgame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.UserRoleInProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.UserRoleInProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserRoleInProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRoleInProjectService {

    private final UserRoleInProjectRepository userRoleInProjectRepository;
    private final ProjectRepository projectRepository;
    private final ObjectMapper objectMapper;

    public List<UserRoleInProject> getRolesOfProject(UUID projectId) {
        List<UserRoleInProjectEntity> roleEntities = userRoleInProjectRepository.findAllByIdProjectId(projectId);

        return convertToDtos(roleEntities);
    }

    public UserRoleInProject getRole(UUID projectId, String roleName) {
        UserRoleInProjectId id = new UserRoleInProjectId(roleName, projectId);

        UserRoleInProjectEntity roleEntity = userRoleInProjectRepository.findByIdOrThrow(id);

        return convertToDto(roleEntity);
    }

    public UserRoleInProject createRole(UUID projectId, CreateRoleInput input) {
        UserRoleInProjectEntity newRoleEntity = UserRoleInProjectEntity.builder()
                .id(new UserRoleInProjectId(input.getName(), projectId))
                // drawback of JPA: we have to set the project entity here
                .project(projectRepository.findByIdOrThrow(projectId))
                .projectPrivileges(input.getPrivileges())
                .gamifiedName(input.getGamifiedName())
                .build();

        UserRoleInProjectEntity savedRoleEntity = userRoleInProjectRepository.save(newRoleEntity);

        return convertToDto(savedRoleEntity);
    }


    public UserRoleInProject updateRole(UUID projectId, String roleName, UpdateRoleInput input) {
        UserRoleInProjectId id = new UserRoleInProjectId(roleName, projectId);
        UserRoleInProjectEntity roleEntity = userRoleInProjectRepository.findByIdOrThrow(id);

        roleEntity.setGamifiedName(input.getGamifiedName());
        roleEntity.setProjectPrivileges(input.getPrivileges());

        UserRoleInProjectEntity savedRoleEntity = userRoleInProjectRepository.save(roleEntity);

        return convertToDto(savedRoleEntity);
    }

    public boolean deleteRole(UUID id, String id1) {
        return false;
    }

    private List<UserRoleInProject> convertToDtos(List<UserRoleInProjectEntity> roleEntities) {
        return roleEntities.stream()
                .map(this::convertToDto)
                .toList();
    }

    private UserRoleInProject convertToDto(UserRoleInProjectEntity userRoleInProjectEntity) {
        return objectMapper.convertValue(userRoleInProjectEntity, UserRoleInProject.class);
    }

}
