package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "global_user_role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalUserRoleEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "gamified_name")
    private String gamifiedName;

    @ElementCollection
    @Builder.Default
    private List<GlobalPrivilege> globalPrivileges = new ArrayList<>();

}