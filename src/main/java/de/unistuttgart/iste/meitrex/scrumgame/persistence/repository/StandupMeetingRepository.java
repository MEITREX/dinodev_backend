package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup.StandupMeetingEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StandupMeetingRepository extends MeitrexRepository<StandupMeetingEntity, UUID> {

    Optional<StandupMeetingEntity> findFirstByProjectIdAndActive(UUID projectId, boolean active);

    @Override
    default String getEntityName() {
        return "StandupMeeting";
    }
}
