package de.unistuttgart.iste.meitrex.scrumgame.service;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GlobalUserService {

    private final AuthService auth;

    @PreAuthorize("@auth.hasGlobalPrivilege('GET_ALL_USERS')")
    public List<GlobalUser> getAllGlobalUserProfiles() {
        // todo actual implementation
        return List.of(getGlobalUserProfile(UUID.randomUUID()));
    }

    // no specific authorization requirements
    public GlobalUser getGlobalUserProfile(UUID userId) {
        return GlobalUser.builder().setId(userId)
                .setUsername("Peter")
                .setRoles(List.of(GlobalUserRole.builder()
                        .setId(UUID.randomUUID())
                        .setName("User")
                        .setGlobalPrivileges(List.of(GlobalPrivilege.CREATE_PROJECT))
                        .build()))
                .build();
    }

    @PreAuthorize("@auth.hasGlobalPrivilege('READ_USER_PRIVATE_INFO')" +
                  " || @auth.currentUserId().equals(#userId)")
    public PrivateUserInfo getPrivateUserInfo(UUID userId) {
        return PrivateUserInfo.builder().build();
    }

    // no specific authorization requirements
    public GlobalUser registerNewUser(CreateUserInput input) {
        return getGlobalUserProfile(UUID.randomUUID());
    }

    @PreAuthorize("@auth.hasGlobalPrivilege('GRANT_ROLE')")
    public GlobalUser grantGlobalRole(UUID targetUserId, UUID roleId) {
        return getGlobalUserProfile(targetUserId);
    }

    @PreAuthorize("@auth.currentUserId().equals(#id) " +
                  "|| @auth.hasGlobalPrivilege('UPDATE_USER')")
    public GlobalUser updateGlobalUserProfile(UUID id, UpdateUserInput input) {
        return getGlobalUserProfile(id);
    }

    // no specific authorization requirements
    public GlobalUser getCurrentUser() {
        return getGlobalUserProfile(auth.getCurrentUserId());
    }
}
