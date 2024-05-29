package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalUserRoleRepository extends MeitrexRepository<GlobalUserRoleEntity, String> {

    @Override
    default String getEntityName() {
        return "GlobalUserRole";
    }
}
