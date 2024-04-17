package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GlobalUserRepository extends MeitrexRepository<GlobalUserEntity, UUID> {
}
