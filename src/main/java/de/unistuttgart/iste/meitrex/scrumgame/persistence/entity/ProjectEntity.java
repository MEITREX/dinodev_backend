package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.UserRoleInProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@ToString(of = {"id", "name"})
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectEntity implements IWithId<UUID> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "end_date")
    private OffsetDateTime endDate;

    @OneToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private List<UserInProjectEntity> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private List<UserRoleInProjectEntity> userRoles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private List<SprintEntity> sprints = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MeetingEntity> meetings = new ArrayList<>();
}
