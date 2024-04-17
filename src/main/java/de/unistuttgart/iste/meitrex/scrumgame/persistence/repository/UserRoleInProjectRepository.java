package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.UserRoleInProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.UserRoleInProjectId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleInProjectRepository extends MeitrexRepository<UserRoleInProjectEntity, UserRoleInProjectId> {

    List<UserRoleInProjectEntity> findAllByIdProjectId(UUID projectId);
}
