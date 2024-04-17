package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "global_user_role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalUserRoleEntity implements IWithId<String> {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private List<GlobalPrivilege> globalPrivileges = new ArrayList<>();

    @Override
    public String getId() {
        return name;
    }
}