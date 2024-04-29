package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.CreateProjectInput;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateProjectInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class ProjectMapping implements Module {

    @Override
    public void setupModule(ModelMapper modelMapper) {

        /* ProjectEntity -> Project */
        modelMapper.createTypeMap(ProjectEntity.class, Project.class);
        // no special mappings needed

        /* CreateProjectInput -> ProjectEntity */
        modelMapper.createTypeMap(CreateProjectInput.class, ProjectEntity.class);
        // no special mappings needed

        /* UpdateProjectInput -> ProjectEntity */
        modelMapper.createTypeMap(UpdateProjectInput.class, ProjectEntity.class);
        // no special mappings needed
    }

}
