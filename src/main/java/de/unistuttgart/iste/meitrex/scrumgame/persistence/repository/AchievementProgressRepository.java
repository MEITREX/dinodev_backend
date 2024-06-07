package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementProgressEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementProgressEntity.AchievementProgressId;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AchievementProgressRepository
        extends MeitrexRepository<AchievementProgressEntity, AchievementProgressId> {

    void deleteAllByIdProjectId(UUID projectId);

    @Override
    default String getEntityName() {
        return "AchievementProgress";
    }
}
