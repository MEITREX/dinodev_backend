package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "global_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalUserEntity implements IWithId<UUID> {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String username;

    @Column(name = "avatar")
    @Lob
    private String avatar;

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

}
