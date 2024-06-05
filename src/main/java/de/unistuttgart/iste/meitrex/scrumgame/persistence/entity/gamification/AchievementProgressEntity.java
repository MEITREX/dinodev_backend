package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementProgressEntity.AchievementProgressId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
public class AchievementProgressEntity implements IWithId<AchievementProgressId> {

    @EmbeddedId
    @ToString.Include
    private AchievementProgressId id;

    @Column
    @Setter
    private int progress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "achievement_identifier")
    @MapsId("achievementIdentifier")
    @Builder.Default
    private AchievementEntity achievement = new AchievementEntity();

    public boolean getAchieved() {
        return progress >= achievement.getGoal();
    }

    public String getAchievementIdentifier() {
        return id.achievementIdentifier;
    }

    public UUID getProjectId() {
        return id.projectId;
    }

    public UUID getUserId() {
        return id.userId;
    }

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class AchievementProgressId implements Serializable {

        @Column(name = "achievement_identifier")
        private String achievementIdentifier;
        private UUID   projectId;
        private UUID   userId;
    }
}
