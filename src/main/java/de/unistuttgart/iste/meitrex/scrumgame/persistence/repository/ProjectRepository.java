package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;

import java.util.UUID;

public interface ProjectRepository extends MeitrexRepository<ProjectEntity, UUID> {

    @Override
    default String getEntityName() {
        return "Project";
    }
}
