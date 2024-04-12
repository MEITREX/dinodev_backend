package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity;

import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectUserRoleEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "gamified_name")
    private String gamifiedName;

    @ElementCollection
    @Builder.Default
    private List<ProjectPrivilege> projectPrivileges = new ArrayList<>();

}
