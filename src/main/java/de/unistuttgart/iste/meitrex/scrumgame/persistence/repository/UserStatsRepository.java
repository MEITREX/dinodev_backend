package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserStatsEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatsRepository extends MeitrexRepository<UserStatsEntity, UserProjectId> {

    @Override
    default String getEntityName() {
        return "UserStats";
    }
}
