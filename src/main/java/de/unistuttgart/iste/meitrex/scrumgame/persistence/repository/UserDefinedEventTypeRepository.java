package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.UserDefinedEventTypeEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDefinedEventTypeRepository extends MeitrexRepository<UserDefinedEventTypeEntity, String> {

    @Override
    default String getEntityName() {
        return "UserDefinedEventType";
    }
}
