package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective.RetrospectiveMeetingEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RetrospectiveMeetingRepository extends MeitrexRepository<RetrospectiveMeetingEntity, UUID> {

    Optional<RetrospectiveMeetingEntity> findFirstByProjectIdAndActive(UUID projectId, boolean active);

    @Override
    default String getEntityName() {
        return "RetrospectiveMeeting";
    }
}
