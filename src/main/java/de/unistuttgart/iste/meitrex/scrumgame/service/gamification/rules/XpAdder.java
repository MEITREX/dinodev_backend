package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserStatsEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class XpAdder {

    private static final int    INITIAL_XP_COST       = 3000;
    private static final int    LINEAR_SCALE_FACTOR   = 250;
    private static final double MULTIPLICATIVE_FACTOR = 1.05;

    public static UserStatsEntity addXp(UserStatsEntity userStats, int xpToAdd) {
        int xpToLevelUp = userStats.getXpToNextLevel();
        int currentXp = userStats.getXp();
        userStats.setTotalXp(userStats.getTotalXp() + xpToAdd);

        if (xpToAdd < xpToLevelUp) {
            userStats.setXp(currentXp + xpToAdd);
        } else {
            userStats.setXp(xpToAdd - xpToLevelUp);
            userStats.setLevel(userStats.getLevel() + 1);
            userStats.setXpToNextLevel(calcXpToNextLevel(userStats.getLevel()));
        }

        return userStats;
    }

    public static int calcXpToNextLevel(int level) {
        return (int) ((INITIAL_XP_COST + (level - 1) * LINEAR_SCALE_FACTOR)
                      * Math.pow(MULTIPLICATIVE_FACTOR, level - 1));
    }

}
