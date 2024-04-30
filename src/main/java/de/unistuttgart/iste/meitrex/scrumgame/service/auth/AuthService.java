package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRoleRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserInProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for authorization checks. Can be used in Spring Security expressions using {@code @auth}.
 */
@Service("auth")
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private static final String REALM_ACCESS_CLAIM = "realm_access";
    private static final String SCRUM_GAME_ADMIN_ROLE = "scrum-game-admin";

    private final GlobalUserRepository     globalUserRepository;
    private final UserInProjectRepository  userInProjectRepository;
    private final GlobalUserRoleRepository globalUserRoleRepository;
    private final ProjectRoleRepository    userRoleInProjectRepository;

    /**
     * Retrieves the user ID of the currently authenticated user. This is read from the JWT token.
     *
     * @return the user UUID
     * @throws AccessDeniedException if the principal is not a JWT or the subject of the JWT is not a valid UUID
     */
    public UUID getCurrentUserId() {
        String subject = getJwt().getSubject();
        try {
            return UUID.fromString(subject);
        } catch (IllegalArgumentException e) {
            throw new AccessDeniedException("Subject in JWT is not a valid UUID: " + subject, e);
        }
    }

    /**
     * Checks if the currently authenticated user has the scrum-game-admin role in Keycloak. This is read from the
     * realm_access claim in the JWT token.
     *
     * @return true if the user has the role, false otherwise
     */
    public boolean hasScrumGameAdminKeycloakRole() {
        Jwt jwt = getJwt();
        var claim = jwt.getClaim(REALM_ACCESS_CLAIM);
        if (claim instanceof Map<?, ?> map) {
            var roles = map.get("roles");
            if (roles instanceof List<?> list) {
                return list.contains(SCRUM_GAME_ADMIN_ROLE);
            }
        }
        return false;
    }

    /**
     * Checks if the currently authenticated user has the given project privilege.
     *
     * @param privilege the privilege to check
     * @param projectId the project ID
     * @return true if the user has the privilege, false otherwise
     */
    public boolean hasPrivilege(ProjectPrivilege privilege, UUID projectId) {
        UUID userId = getCurrentUserId();
        boolean authorized = getProjectPrivileges(userId, projectId).contains(privilege);

        if (!authorized) {
            log.warn("Unauthorized access attempt: {} is missing project privilege {} for project {}",
                    getCurrentUserId(), privilege, projectId);
        }
        return authorized;
    }

    /**
     * Checks if the currently authenticated user has the given global privilege.
     *
     * @param privilege the privilege to check
     * @return true if the user has the privilege, false otherwise
     */
    public boolean hasPrivilege(GlobalPrivilege privilege) {
        UUID userId = getCurrentUserId();
        boolean authorized = getGlobalPrivileges(userId).contains(privilege);

        if (!authorized) {
            log.warn("Unauthorized access attempt: {} is missing global privilege {}",
                    getCurrentUserId(), privilege);
        }
        return authorized;
    }

    /**
     * Checks if the currently authenticated user has all privileges of the given global role.
     *
     * @param roleName the name of the global role
     * @return true if the user has all privileges of the role, false otherwise
     * @throws MeitrexNotFoundException if the role does not exist
     */
    public boolean hasPrivilegesOfGlobalRole(String roleName) {
        UUID userId = getCurrentUserId();
        GlobalUserRoleEntity role = globalUserRoleRepository.findByIdOrThrow(roleName);

        return getGlobalPrivileges(userId)
                .containsAll(role.getGlobalPrivileges());
    }

    /**
     * Checks if the currently authenticated user has all privileges of the given project role in the given project.
     *
     * @param roleName  the name of the project role
     * @param projectId the project ID
     * @return true if the user has all privileges of the role, false otherwise
     * @throws MeitrexNotFoundException if the role does not exist
     */
    public boolean hasPrivilegesOfProjectRole(String roleName, UUID projectId) {
        UUID userId = getCurrentUserId();
        ProjectRoleEntity role = userRoleInProjectRepository
                .findByIdOrThrow(new ProjectRoleId(roleName, projectId));

        return getProjectPrivileges(userId, projectId)
                .containsAll(role.getProjectPrivileges());
    }

    private Set<ProjectPrivilege> getProjectPrivileges(UUID userId, UUID projectId) {
        UserProjectId userProjectId = new UserProjectId(userId, projectId);

        List<ProjectRoleEntity> roles = userInProjectRepository.findById(userProjectId)
                .map(UserInProjectEntity::getRoles)
                .orElse(Collections.emptyList());

        return roles.stream()
                .flatMap(role -> role.getProjectPrivileges().stream())
                .collect(Collectors.toUnmodifiableSet());
    }

    private Set<GlobalPrivilege> getGlobalPrivileges(UUID userId) {
        List<GlobalUserRoleEntity> roles = globalUserRepository.findById(userId)
                .map(GlobalUserEntity::getRoles)
                .orElse(Collections.emptyList());

        return roles.stream()
                .flatMap(role -> role.getGlobalPrivileges().stream())
                .collect(Collectors.toUnmodifiableSet());
    }

    private Jwt getJwt() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof Jwt jwt) {
            return jwt;
        }
        throw new AccessDeniedException("Principal is not a JWT. Did you provide a valid access token?");
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
