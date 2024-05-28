package de.unistuttgart.iste.meitrex.scrumgame.service.user;

import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils;
import de.unistuttgart.iste.meitrex.generated.dto.PrivateUserInfo;
import de.unistuttgart.iste.meitrex.generated.dto.UserInProject;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.PrivateUserInfoEmbeddable;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserInProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Getter(AccessLevel.PROTECTED)
public class UserInProjectService
        extends AbstractCrudService<UserProjectId, UserInProjectEntity, UserInProject> {

    private final UserInProjectRepository repository;
    private final AuthService                     authService;
    private final UserInProjectInitializerService userInProjectInitializerService;

    public UserInProjectService(
            UserInProjectRepository repository,
            AuthService authService,
            UserInProjectInitializerService userInProjectInitializerService,
            ModelMapper modelMapper
    ) {
        super(repository, modelMapper, UserInProjectEntity.class, UserInProject.class);
        this.repository = repository;
        this.authService = authService;
        this.userInProjectInitializerService = userInProjectInitializerService;
    }

    /**
     * Finds a user in a project.
     *
     * @param userId    the user ID
     * @param projectId the project ID
     * @return the user in project or empty if not found
     */
    public Optional<UserInProject> findUserInProject(UUID userId, UUID projectId) {
        return find(new UserProjectId(userId, projectId));
    }

    /**
     * Finds all {@link UserInProject}s in a project.
     *
     * @param projectId the project ID
     * @return the list of users in project
     */
    public List<UserInProject> getUsersInProjectByProjectId(UUID projectId) {
        List<UserInProjectEntity> userInProjectEntities = repository.findAllByProjectId(projectId);
        return convertToDtos(userInProjectEntities);
    }

    /**
     * Finds all {@link UserInProject}s of a user.
     *
     * @param userId the user ID
     * @return the list of {@link UserInProject}s of the user
     */
    public List<UserInProject> getUserInProjectsByUserId(UUID userId) {
        List<UserInProjectEntity> userInProjectEntities = repository.findAllByUserId(userId);
        return convertToDtos(userInProjectEntities);
    }

    /**
     * Finds the current user in a project. If the current user is not part of the project, an empty optional is
     * returned.
     *
     * @param projectId the project ID
     * @return the current user in project or empty if not found
     */
    public Optional<UserInProject> findCurrentUserInProject(UUID projectId) {
        UUID currentUserId = authService.getCurrentUserId();
        return findUserInProject(currentUserId, projectId);
    }

    /**
     * Resolves the private information of a user in a project. The private information is only accessible by the user
     * itself.
     *
     * @param userId the user ID
     * @return the private information of the user in the project
     */
    @PreAuthorize("@auth.currentUserId.equals(#userId) " +
                  "or @auth.hasPrivilege(@projectPrivileges.READ_USER_PRIVATE_INFO, #projectId)")
    public PrivateUserInfo getPrivateInfo(UUID userId, UUID projectId) {
        PrivateUserInfoEmbeddable privateInfo = repository
                .findByIdOrThrow(new UserProjectId(userId, projectId))
                .getPrivateInfo();

        return getModelMapper().map(privateInfo, PrivateUserInfo.class);
    }

    public UserInProject createUserInProject(UUID userId, UUID projectId) {
        UserInProjectEntity userInProjectEntity = userInProjectInitializerService
                .createUserInProject(userId, projectId);

        return convertToDto(userInProjectEntity);
    }

    public UserInProject grantRoleToUser(UUID userId, UUID projectId, String roleName) {
        UserInProjectEntity userInProjectEntity = userInProjectInitializerService
                .grantRoleToUser(userId, projectId, roleName);

        return convertToDto(userInProjectEntity);
    }

    public UserInProject joinProject(UUID projectId) {
        UUID currentUserId = authService.getCurrentUserId();

        return createUserInProject(currentUserId, projectId);
    }

    public List<UserInProject> getUsers(UUID projectId, List<UUID> userIds) {
        List<UserInProject> allUsers = getUsersInProjectByProjectId(projectId);
        return MeitrexCollectionUtils.sortByKeys(allUsers, userIds, UserInProject::getUserId);
    }

}
