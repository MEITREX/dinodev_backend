package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString(of = {"id", "gamifiedName", "projectPrivileges"})
@Table(name = "user_role_in_project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRoleEntity implements IWithId<ProjectRoleId> {

    @EmbeddedId
    @Setter
    private ProjectRoleId id;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(name = "gamified_name")
    @Setter
    private String gamifiedName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Setter
    private List<ProjectPrivilege> projectPrivileges = new ArrayList<>();

    public String getName() {
        return id.getName();
    }

}
