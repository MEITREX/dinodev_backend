package de.unistuttgart.iste.meitrex.scrumgame.matchers;

import de.unistuttgart.iste.meitrex.generated.dto.CreateProjectRoleInput;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectRole;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateProjectRoleInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.*;

public class ProjectRoleMatcher {

    private ProjectRoleMatcher() {
    }

    public static Matcher<ProjectRole> matchingProjectRoleEntity(ProjectRoleEntity projectRoleEntity) {
        return allOf(
                hasProperty("name", is(projectRoleEntity.getId().getName())),
                hasProperty("projectPrivileges", containsInAnyOrder(
                        projectRoleEntity.getProjectPrivileges().toArray(ProjectPrivilege[]::new))),
                hasProperty("gamifiedName", is(projectRoleEntity.getGamifiedName())),
                hasProperty("projectId", is(projectRoleEntity.getId().getProjectId()))
        );
    }

    public static <T> Matcher<T> matchingProjectRoleInput(CreateProjectRoleInput createProjectRoleInput) {
        return allOf(
                hasProperty("name", is(createProjectRoleInput.getName())),
                hasProperty("projectPrivileges", containsInAnyOrder(
                        createProjectRoleInput.getProjectPrivileges().toArray(ProjectPrivilege[]::new))),
                hasProperty("gamifiedName", is(createProjectRoleInput.getGamifiedName()))
        );
    }

    public static <T> Matcher<T> matchingProjectRoleInput(UpdateProjectRoleInput updateProjectRoleInput) {
        return allOf(
                hasProperty("projectPrivileges", containsInAnyOrder(
                        updateProjectRoleInput.getProjectPrivileges().toArray(ProjectPrivilege[]::new))),
                hasProperty("gamifiedName", is(updateProjectRoleInput.getGamifiedName()))
        );
    }
}
