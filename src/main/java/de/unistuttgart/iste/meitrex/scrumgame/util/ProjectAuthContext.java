package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@Getter
public class ProjectAuthContext extends DefaultAuthContext {

    private final UUID projectId;

    public ProjectAuthContext(Authentication authentication, UUID projectId) {
        super(authentication);
        this.projectId = projectId;
    }

    @Override
    public boolean hasProjectPrivilege(ProjectPrivilege privilege) {
        return super.hasProjectPrivilege(privilege);
    }

    @Override
    public boolean isMeetingLeader() {
        return super.isMeetingLeader();
    }

    public static ProjectAuthContext of(UUID projectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ProjectAuthContext(authentication, projectId);
    }
}
