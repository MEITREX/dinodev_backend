package de.unistuttgart.iste.meitrex.scrumgame.data;

import de.unistuttgart.iste.meitrex.generated.dto.CreateProjectRoleInput;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateProjectRoleInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleId;

import java.util.List;

public class SampleProjectRoles {

    private SampleProjectRoles() {
    }

    public static ProjectRoleEntity.ProjectRoleEntityBuilder sampleProjectRoleBuilder(ProjectEntity project,
            String name) {
        return ProjectRoleEntity.builder()
                .id(new ProjectRoleId(name, project.getId()))
                .project(project)
                .gamifiedName("GamifiedName")
                .projectPrivileges(List.of(ProjectPrivilege.READ_PROJECT, ProjectPrivilege.UPDATE_PROJECT));
    }

    public static CreateProjectRoleInput sampleCreateProjectRoleInput() {
        return CreateProjectRoleInput.builder()
                .setName("Name")
                .setGamifiedName("GamifiedName")
                .setProjectPrivileges(List.of(ProjectPrivilege.READ_PROJECT, ProjectPrivilege.UPDATE_PROJECT))
                .build();
    }

    public static UpdateProjectRoleInput sampleUpdateProjectRoleInput() {
        return UpdateProjectRoleInput.builder()
                .setGamifiedName("Updated GamifiedName")
                .setProjectPrivileges(List.of(ProjectPrivilege.UPDATE_ROLE, ProjectPrivilege.DELETE_SPRINT))
                .build();
    }

}
