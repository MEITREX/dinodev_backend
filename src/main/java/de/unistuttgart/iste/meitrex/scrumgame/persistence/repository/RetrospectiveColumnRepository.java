package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective.RetrospectiveColumnEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RetrospectiveColumnRepository extends MeitrexRepository<RetrospectiveColumnEntity, UUID> {

    @Override
    default String getEntityName() {
        return "RetrospectiveColumn";
    }
}
