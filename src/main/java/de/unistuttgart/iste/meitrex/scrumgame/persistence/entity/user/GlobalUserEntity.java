package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Entity
@Table(name = "global_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalUserEntity implements IWithId<UUID> {

    @Id
    @Column(name = "id", nullable = false)
    @Setter
    private UUID id;

    @Column(name = "name")
    @Setter
    private String username;

    @Column(name = "avatar")
    @Setter
    @Lob
    private String avatar;

    @Column
    @Setter
    private String vcsUserId;

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private List<GlobalUserRoleEntity> roles = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    private List<UserInProjectEntity> userInProjects = new ArrayList<>();

    @PreRemove
    public void onDelete() {
        for (GlobalUserRoleEntity role : roles) {
            role.getUsers().remove(this);
        }
    }

    public void setRoles(List<GlobalUserRoleEntity> roles) {
        this.roles = MeitrexCollectionUtils.replaceContent(this.roles, roles);
    }
}
