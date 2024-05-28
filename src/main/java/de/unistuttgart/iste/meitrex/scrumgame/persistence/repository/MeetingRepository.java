package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.generated.dto.MeetingType;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning.PlanningMeetingEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MeetingRepository extends MeitrexRepository<MeetingEntity, UUID> {

    /**
     * Find the top meeting by project id, meeting type and active.
     * Remark that there should be only one active meeting of a certain type per project.
     *
     * @param projectId   the project id
     * @param meetingType the meeting type
     * @param active      active flag
     * @return the optional meeting entity
     */
    Optional<MeetingEntity> findTopByProjectIdAndMeetingTypeAndActive(
            UUID projectId,
            MeetingType meetingType,
            boolean active
    );

    @Override
    default String getEntityName() {
        return "Meeting";
    }
}
