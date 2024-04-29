package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint.SprintEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
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
    @Setter
    private String name;

    @Column(name = "description")
    @Setter
    private String description;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_settings_id")
    @Setter
    private ProjectSettingsEntity projectSettings;
}
