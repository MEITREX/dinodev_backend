package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.ProjectRole;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateProjectRoleInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class ProjectRoleMapping implements Module {

    @Override
    public void setupModule(ModelMapper modelMapper) {
        /* ProjectRoleEntity -> UserRoleInProject */
        modelMapper.createTypeMap(ProjectRoleEntity.class, ProjectRole.class)
                .addMapping(entity -> entity.getId().getProjectId(), ProjectRole::setProjectId)
                .addMapping(entity -> entity.getId().getName(), ProjectRole::setName);

        // no type map for CreateProjectRoleInput -> ProjectRoleEntity needed, because the mapping is done manually

        /* UpdateProjectRoleInput -> ProjectRoleEntity */
        modelMapper.emptyTypeMap(UpdateProjectRoleInput.class, ProjectRoleEntity.class)
                .addMappings(mapper -> mapper.skip(ProjectRoleEntity::setId))
                .implicitMappings();
    }
}
