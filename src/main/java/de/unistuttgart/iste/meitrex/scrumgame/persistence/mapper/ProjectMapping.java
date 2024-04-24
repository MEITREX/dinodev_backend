package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class ProjectMapping implements Module {
    @Override
    public void setupModule(ModelMapper modelMapper) {

        /* ProjectEntity -> Project */
        modelMapper.emptyTypeMap(ProjectEntity.class, Project.class)
                .addMappings(mapper -> {
                    // lazily resolved by schema mapping
                    mapper.skip(ProjectEntity::getUsers, Project::setUsers);
                    mapper.skip(ProjectEntity::getUserRoles, Project::setRoles);
                    mapper.skip(ProjectEntity::getSprints, Project::setSprints);
                })

                .implicitMappings();

        /* CreateProjectInput -> ProjectEntity */
        modelMapper.createTypeMap(CreateProjectInput.class, ProjectEntity.class);
        // no special mappings needed

        /* UpdateProjectInput -> ProjectEntity */
        modelMapper.createTypeMap(UpdateProjectInput.class, ProjectEntity.class);
        // no special mappings needed
    }
}
