package de.unistuttgart.iste.meitrex.scrumgame.service;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.role.UserRoleInProjectService;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.UserInProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectInitializerService {

    private final UserInProjectService userInProjectService;
    private final AuthService auth;
    private final UserRoleInProjectService userRoleInProjectService;

    /**
     * Initializes a new project by creating the necessary roles and adding the current user as an admin.
     *
     * @param project the project to initialize.
     */
    public void initializeNewProject(ProjectEntity project) {
        userRoleInProjectService.getOrCreateAdminRole(project.getId());
        userRoleInProjectService.getOrCreateDefaultRole(project.getId());

        userInProjectService.createUserInProject(auth.getCurrentUserId(), project.getId());

        userInProjectService.grantRoleToUser(
                auth.getCurrentUserId(),
                project.getId(),
                UserRoleInProjectService.ADMIN_ROLE_NAME);
    }
}
