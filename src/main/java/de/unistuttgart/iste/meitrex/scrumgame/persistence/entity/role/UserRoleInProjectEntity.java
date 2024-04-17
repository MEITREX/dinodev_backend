package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.ProjectEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString(of = {"id", "gamifiedName", "projectPrivileges"})
@Table(name = "user_role_in_project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleInProjectEntity implements IWithId<UserRoleInProjectId> {

    @EmbeddedId
    private UserRoleInProjectId id;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(name = "gamified_name")
    private String gamifiedName;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private List<ProjectPrivilege> projectPrivileges = new ArrayList<>();


}
