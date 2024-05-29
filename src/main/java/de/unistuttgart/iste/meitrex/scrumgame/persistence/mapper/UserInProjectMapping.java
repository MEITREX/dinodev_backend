package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.UserInProject;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class UserInProjectMapping implements Module {

    @Override
    public void setupModule(ModelMapper modelMapper) {
        modelMapper.emptyTypeMap(UserInProjectEntity.class, UserInProject.class)
                // custom mappings
                .addMapping(entity -> entity.getId().getProjectId(), UserInProject::setProjectId)
                .addMapping(entity -> entity.getId().getUserId(), UserInProject::setUserId)

                .implicitMappings();
    }

}
