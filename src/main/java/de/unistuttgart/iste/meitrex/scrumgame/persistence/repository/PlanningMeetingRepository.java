package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning.PlanningMeetingEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface PlanningMeetingRepository extends MeitrexRepository<PlanningMeetingEntity, UUID> {

    Optional<PlanningMeetingEntity> findFirstByProjectIdAndActive(UUID projectId, boolean active);

    @Override
    default String getEntityName() {
        return "PlanningMeeting";
    }
}
