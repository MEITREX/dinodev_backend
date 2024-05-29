package de.unistuttgart.iste.meitrex.scrumgame.service.user;

import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.BasicUserInfo;
import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserInput;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthConnector;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.role.GlobalUserRoleService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class GlobalUserService extends AbstractCrudService<UUID, GlobalUserEntity, GlobalUser> {

    private final AuthService          auth;
    private final GlobalUserRepository globalUserRepository;
    private final GlobalUserRoleService globalUserRoleService;
    private final AuthConnector        authConnector;

    public GlobalUserService(
            ModelMapper modelMapper,
            AuthService auth,
            GlobalUserRepository globalUserRepository,
            GlobalUserRoleService globalUserRoleService,
            AuthConnector authConnector
    ) {
        super(globalUserRepository, modelMapper, GlobalUserEntity.class, GlobalUser.class);
        this.auth = auth;
        this.globalUserRepository = globalUserRepository;
        this.globalUserRoleService = globalUserRoleService;
        this.authConnector = authConnector;
    }

    public List<GlobalUser> getAllGlobalUsers() {
        return getAll();
    }

    public Optional<GlobalUser> findGlobalUser(UUID userId) {
        if (userId == null) {
            return Optional.empty();
        }
        return find(userId);
    }

    public List<GlobalUser> findUsersBatched(List<UUID> userIds) {
        List<GlobalUserEntity> entities = globalUserRepository.findAllByIdPreservingOrder(userIds);
        return convertToDtos(entities);
    }

    public Optional<UUID> findUserByGithubId(String githubId) {
        return globalUserRepository.findTopByVcsUserId(githubId)
                .map(GlobalUserEntity::getId);
    }

    @PreAuthorize("@auth.hasPrivilegesOfGlobalRole(#roleName) and @auth.hasPrivilege(@globalPrivileges.CHANGE_ROLES)")
    public GlobalUser grantRole(UUID userId, String roleName) {
        GlobalUserRoleEntity roleEntity = globalUserRoleService.getGlobalUserRoleEntity(roleName);

        return update(userId, user -> user.getRoles().add(roleEntity));
    }

    @PreAuthorize("@auth.hasPrivilegesOfGlobalRole(#roleName) and @auth.hasPrivilege(@globalPrivileges.CHANGE_ROLES)")
    public GlobalUser revokeRole(UUID userId, String roleName) {
        GlobalUserRoleEntity roleEntity = globalUserRoleService.getGlobalUserRoleEntity(roleName);

        return update(userId, user -> user.getRoles().remove(roleEntity));
    }

    public GlobalUser registerNewUser(CreateGlobalUserInput input) {
        globalUserRepository.requireNotExists(auth.getCurrentUserId());

        return create(() -> initNewUser(input));
    }

    @PreAuthorize("@auth.currentUserId.equals(#id) or @auth.hasPrivilege(@globalPrivileges.UPDATE_USER)")
    public GlobalUser updateGlobalUser(UUID id, UpdateGlobalUserInput input) {
        return update(id, input);
    }

    public Optional<GlobalUser> findCurrentUser() {
        return findGlobalUser(auth.getCurrentUserId());
    }

    private GlobalUserEntity initNewUser(CreateGlobalUserInput input) {
        return GlobalUserEntity.builder()
                .id(auth.getCurrentUserId())
                .username(input.getUsername())
                .avatar(input.getAvatar())
                .roles(getRolesForNewUser())
                .vcsUserId(input.getVcsUserId())
                .build();
    }

    private List<GlobalUserRoleEntity> getRolesForNewUser() {
        List<GlobalUserRoleEntity> roles = new ArrayList<>();

        if (isAdminInIms()) {
            roles.add(globalUserRoleService.getOrCreateAdminRole());
        }

        return roles;
    }

    private boolean isAdminInIms() {
        return authConnector.getUser().map(BasicUserInfo::getIsAdmin).orElse(false);
    }

}
