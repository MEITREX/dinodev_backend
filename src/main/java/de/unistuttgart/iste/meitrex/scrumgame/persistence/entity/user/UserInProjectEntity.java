package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.UserRoleInProjectEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_in_project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInProjectEntity implements IWithId<UserProjectId> {

    @EmbeddedId
    private UserProjectId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private GlobalUserEntity user;

    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToMany
    @Builder.Default
    private List<UserRoleInProjectEntity> roles = new ArrayList<>();

    @Embedded
    private PrivateUserInfoEmbeddable privateInfo;

}
