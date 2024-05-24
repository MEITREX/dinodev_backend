package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint.SprintEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

@Getter
@Entity
@ToString(of = {"id", "name"})
@Table(name = "project")
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity implements IWithId<UUID> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    @Setter
    private String name;

    @Column(name = "description")
    @Setter
    private String description;

    @Column
    @Nullable
    @Setter
    private Integer currentSprintNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<UserInProjectEntity> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProjectRoleEntity> userRoles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SprintEntity> sprints = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "project")
    @Builder.Default
    private List<MeetingEntity> meetings = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_settings_id")
    @Setter
    private ProjectSettingsEntity projectSettings;
}
