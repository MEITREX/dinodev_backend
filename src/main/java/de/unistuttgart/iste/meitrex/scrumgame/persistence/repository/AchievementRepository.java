package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends MeitrexRepository<AchievementEntity, String> {

    @Override
    default String getEntityName() {
        return "Achievement";
    }

}
