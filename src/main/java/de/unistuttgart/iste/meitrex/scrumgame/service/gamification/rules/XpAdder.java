package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserStatsEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class XpAdder {

    private static final int    INITIAL_XP_COST       = 3000;
    private static final int    LINEAR_SCALE_FACTOR   = 250;
    private static final double MULTIPLICATIVE_FACTOR = 1.01;

    public static UserStatsEntity addXp(UserStatsEntity userStats, int xpToAdd) {
        int xpToLevelUp = userStats.getXpToNextLevel();
        int currentXp = userStats.getXp();
        userStats.setTotalXp(userStats.getTotalXp() + xpToAdd);

        currentXp += xpToAdd;

        while (currentXp >= xpToLevelUp) {
            currentXp -= xpToLevelUp;
            userStats.setLevel(userStats.getLevel() + 1);
            xpToLevelUp = calcXpToNextLevel(userStats.getLevel());
        }

        userStats.setXp(currentXp);
        userStats.setXpToNextLevel(xpToLevelUp);

        return userStats;
    }

    public static int calcXpToNextLevel(int level) {
        return (int) (
                (INITIAL_XP_COST + (level - 1) * LINEAR_SCALE_FACTOR)
                * Math.pow(MULTIPLICATIVE_FACTOR, (double) level - 1));
    }

}
