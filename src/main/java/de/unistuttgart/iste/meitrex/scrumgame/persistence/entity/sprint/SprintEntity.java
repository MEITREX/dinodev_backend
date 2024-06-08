package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.generated.dto.Animal;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@ToString(of = {"id", "name", "number"})
@Table(name = "sprint")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SprintEntity implements IWithId<UUID> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    @Nullable
    private String name;

    @Column
    @Nullable
    private Animal animal;

    @Column
    private int number;

    @Column
    private OffsetDateTime startDate;

    @Column
    private OffsetDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column
    @Nullable
    private Integer storyPointsPlanned;

    @Column
    @Nullable
    private String customGoldChallengeReward;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PlacedAssetEntity> placedAssets = new ArrayList<>();
}
