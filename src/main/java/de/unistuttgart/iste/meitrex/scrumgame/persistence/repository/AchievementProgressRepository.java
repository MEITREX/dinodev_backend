package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementProgressEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementProgressEntity.AchievementProgressId;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementProgressRepository
        extends MeitrexRepository<AchievementProgressEntity, AchievementProgressId> {

    @Override
    default String getEntityName() {
        return "AchievementProgress";
    }
}
