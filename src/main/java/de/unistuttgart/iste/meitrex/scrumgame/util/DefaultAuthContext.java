package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;


/**
 * Parameter object containing the context for project level operations.
 */
@Getter
@RequiredArgsConstructor
public class DefaultAuthContext implements AuthContext {
    private final Authentication authentication;

    @Override
    public boolean hasProjectPrivilege(ProjectPrivilege privilege) {
        return false;
    }

    @Override
    public boolean hasGlobalPrivilege(GlobalPrivilege privilege) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(privilege.toString()::equals);
    }

    @Override
    public boolean isMeetingLeader() {
        return false;
    }

    @Override
    public UUID getUserId() {
        Object principal = authentication.getPrincipal();
        if (principal instanceof Jwt jwt) {
            String subject = jwt.getSubject();
            return UUID.fromString(subject);
        }
        throw new IllegalStateException("Principal is not a JWT");
    }

    public static DefaultAuthContext defaultAuthContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new DefaultAuthContext(authentication);
    }


}
