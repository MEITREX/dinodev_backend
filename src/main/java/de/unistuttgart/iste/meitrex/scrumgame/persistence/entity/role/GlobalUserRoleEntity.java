package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "global_user_role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalUserRoleEntity implements IWithId<String> {
    @Id
    @Column(name = "name", nullable = false)
    @Setter
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Setter
    private List<GlobalPrivilege> globalPrivileges = new ArrayList<>();

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Builder.Default
    private List<GlobalUserEntity> users = new ArrayList<>();

    @Override
    public String getId() {
        return name;
    }

    @PreRemove
    public void onDelete() {
        for (GlobalUserEntity user : users) {
            user.getRoles().remove(this);
        }
    }
}