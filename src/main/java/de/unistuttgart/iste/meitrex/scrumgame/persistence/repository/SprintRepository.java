package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint.SprintEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SprintRepository extends MeitrexRepository<SprintEntity, UUID> {

    List<SprintEntity> findAllByProjectIdOrderByNumber(UUID projectId);

    Optional<SprintEntity> findByProjectIdAndNumber(UUID projectId, Integer sprintNumber);

    @Override
    default String getEntityName() {
        return "Sprint";
    }
}
