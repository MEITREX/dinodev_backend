package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("auth")
@Slf4j
public class AuthService {

    public UUID getCurrentUserId() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof Jwt jwt) {
            String subject = jwt.getSubject();
            return UUID.fromString(subject);
        }
        throw new IllegalStateException("Principal is not a JWT");
    }

    public boolean hasProjectPrivilege(ProjectPrivilege privilege, UUID projectId) {
        return false; // TODO implement

    }

    public boolean hasGlobalPrivilege(GlobalPrivilege privilege) {
        boolean authorized = getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(privilege.toString()::equals);

        if (!authorized) {
            log.warn("Unauthorized access attempt: {} is missing global privilege {}", getCurrentUserId(), privilege);
        }
        return authorized;
    }

    public boolean isMeetingLeader(UUID projectId, UUID meetingId) {
        return false; // TODO implement

    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
