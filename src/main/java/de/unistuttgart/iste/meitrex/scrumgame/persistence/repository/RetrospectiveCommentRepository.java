package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective.RetrospectiveCommentEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RetrospectiveCommentRepository extends MeitrexRepository<RetrospectiveCommentEntity, UUID> {

    @Override
    default String getEntityName() {
        return "RetrospectiveComment";
    }

}
