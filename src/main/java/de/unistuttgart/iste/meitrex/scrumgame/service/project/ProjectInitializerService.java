package de.unistuttgart.iste.meitrex.scrumgame.service.project;

import de.unistuttgart.iste.meitrex.generated.dto.CreateSprintInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.role.ProjectRoleService;
import de.unistuttgart.iste.meitrex.scrumgame.service.sprint.SprintService;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.UserInProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectInitializerService {

    private final UserInProjectService userInProjectService;
    private final AuthService        auth;
    private final ProjectRoleService userRoleInProjectService;
    private final SprintService      sprintService;

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
                ProjectRoleService.ADMIN_ROLE_NAME);

        // create previous sprints
        if (project.getCurrentSprintNumber() != null) {
            for (int i = 1; i < project.getCurrentSprintNumber(); i++) {
                sprintService.createSprintWithNumber(project, i, new CreateSprintInput());
            }
        }
    }
}
