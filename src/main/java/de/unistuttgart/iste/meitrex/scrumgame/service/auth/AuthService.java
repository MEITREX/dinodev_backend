package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.UserRoleInProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
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
 * Service for authorization checks.
 * Can be used in Spring Security expressions using {@code @auth}.
 */
@Service("auth")
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final GlobalUserRepository globalUserRepository;
    private final UserInProjectRepository userInProjectRepository;

    /**
     * Retrieves the user ID of the currently authenticated user.
     * This is read from the JWT token.
     *
     * @return the user UUID
     * @throws AccessDeniedException    if the principal is not a JWT
     * @throws IllegalArgumentException if the subject of the JWT is not a valid UUID
     */
    public UUID getCurrentUserId() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof Jwt jwt) {
            String subject = jwt.getSubject();
            return UUID.fromString(subject);
        }
        throw new AccessDeniedException("Principal is not a JWT. Did you provide a valid access token?");
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

    private Set<ProjectPrivilege> getProjectPrivileges(UUID userId, UUID projectId) {
        UserProjectId userProjectId = new UserProjectId(userId, projectId);

        List<UserRoleInProjectEntity> roles = userInProjectRepository.findById(userProjectId)
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

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
