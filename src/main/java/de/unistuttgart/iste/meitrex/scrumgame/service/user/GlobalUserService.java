package de.unistuttgart.iste.meitrex.scrumgame.service.user;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserInput;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GlobalUserService extends AbstractCrudService<UUID, GlobalUserEntity, GlobalUser> {

    private final AuthService auth;
    private final GlobalUserRepository globalUserRepository;
    private final ModelMapper modelMapper;
    private final GlobalUserRoleService globalUserRoleService;

    public List<GlobalUser> getAllGlobalUsers() {
        return getAll();
    }

    public Optional<GlobalUser> findGlobalUser(UUID userId) {
        return find(userId);
    }

    public GlobalUser getGlobalUserOrThrow(UUID userId) {
        return getOrThrow(userId);
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
                .build();
    }

    private List<GlobalUserRoleEntity> getRolesForNewUser() {
        List<GlobalUserRoleEntity> roles = new ArrayList<>();

        if (auth.hasScrumGameAdminKeycloakRole()) {
            roles.add(globalUserRoleService.getOrCreateAdminRole());
        }

        return roles;
    }

    @Override
    protected Class<GlobalUserEntity> getEntityClass() {
        return GlobalUserEntity.class;
    }

    @Override
    protected Class<GlobalUser> getDtoClass() {
        return GlobalUser.class;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    protected MeitrexRepository<GlobalUserEntity, UUID> getRepository() {
        return globalUserRepository;
    }
}
