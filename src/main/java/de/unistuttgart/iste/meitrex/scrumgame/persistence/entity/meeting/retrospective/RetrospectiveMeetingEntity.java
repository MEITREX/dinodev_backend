package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective;

import de.unistuttgart.iste.meitrex.generated.dto.Animal;
import de.unistuttgart.iste.meitrex.generated.dto.KnownAsset;
import de.unistuttgart.iste.meitrex.generated.dto.RetrospectiveMeetingPage;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils.replaceContent;

@Entity
@Table(name = "retrospective_meeting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RetrospectiveMeetingEntity extends MeetingEntity {

    private RetrospectiveMeetingPage currentPage;

    private UUID goldMedalUserId;
    private UUID silverMedalUserId;
    private UUID bronzeMedalUserId;

    private double goldMedalPoints;
    private double silverMedalPoints;
    private double bronzeMedalPoints;

    private Animal goldChallengeReward;

    private boolean medalsAwarded;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RetrospectiveActivityEntity> activities = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<KnownAsset> baseRewards = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<KnownAsset> streakRewards = new HashSet<>();

    public void setActivities(List<RetrospectiveActivityEntity> activities) {
        this.activities = replaceContent(this.activities, activities);
    }

    public void setBaseRewards(Set<KnownAsset> baseRewards) {
        this.baseRewards = replaceContent(this.baseRewards, baseRewards);
    }

    public void setStreakRewards(Set<KnownAsset> streakRewards) {
        this.streakRewards = replaceContent(this.streakRewards, streakRewards);
    }
}
