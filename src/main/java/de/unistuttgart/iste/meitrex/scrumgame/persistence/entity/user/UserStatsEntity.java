package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "user_stats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserStatsEntity implements IWithId<UserProjectId> {

    // weak link to user and project, so that stats can be counted even if the user isn't registered yet
    @EmbeddedId
    private UserProjectId id;

    private int totalXp;
    private int xp;
    private int xpToNextLevel;
    private int level;

    private int issuesCompleted;
    private int issuesCreated;
    private int commentsWritten;
    private int reactionsGiven;
    private int pullRequestsCreated;
    private int pullRequestsClosed;
    private int pullRequestsReviewed;

    private int virtualCurrency;

    private int goldMedals;
    private int silverMedals;
    private int bronzeMedals;

}
