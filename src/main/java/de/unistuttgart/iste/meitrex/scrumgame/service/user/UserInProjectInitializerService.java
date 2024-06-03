package de.unistuttgart.iste.meitrex.scrumgame.service.user;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserInProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.role.ProjectRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserInProjectInitializerService {

    private final ProjectRepository       projectRepository;
    private final GlobalUserRepository    globalUserRepository;
    private final UserInProjectRepository userInProjectRepository;

    private final ProjectRoleService userRoleInProjectService;

    public UserInProjectEntity createUserInProject(UUID userId, UUID projectId) {
        UserInProjectEntity userInProjectEntity = UserInProjectEntity.builder()
                .id(new UserProjectId(userId, projectId))
                .user(globalUserRepository.findByIdOrThrow(userId))
                .project(projectRepository.findByIdOrThrow(projectId))
                .build();

        userInProjectRepository.save(userInProjectEntity);

        // userRoleInProjectService.getOrCreateDefaultRole(projectId);
        // grantRoleToUser(userId, projectId, UserRoleInProjectService.DEFAULT_ROLE_NAME);

        return userInProjectEntity;
    }

    public UserInProjectEntity grantRoleToUser(UUID userId, UUID projectId, String roleName) {
        UserInProjectEntity userInProjectEntity = userInProjectRepository
                .findByIdOrThrow(new UserProjectId(userId, projectId));

        userInProjectEntity.getRoles().add(userRoleInProjectService.getRoleEntity(projectId, roleName));

        return userInProjectRepository.save(userInProjectEntity);
    }

}
