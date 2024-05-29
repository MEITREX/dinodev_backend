package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanningSettingsEmbeddable {

    @Column(nullable = false)
    private int sprintDurationDays;

    @Column(nullable = false)
    private OffsetDateTime sprintStartDate;

    @Column(nullable = true)
    private String customGoldChallengeReward;
}
