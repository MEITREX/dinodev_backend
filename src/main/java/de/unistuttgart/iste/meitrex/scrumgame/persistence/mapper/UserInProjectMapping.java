package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.UserInProject;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class UserInProjectMapping implements Module {

    @Override
    public void setupModule(ModelMapper modelMapper) {
        modelMapper.emptyTypeMap(UserInProjectEntity.class, UserInProject.class)
                // fields resolved only if required using schema mapping
                .addMappings(mapper -> mapper.skip(UserInProjectEntity::getProject, UserInProject::setProject))
                .addMappings(mapper -> mapper.skip(UserInProjectEntity::getUser, UserInProject::setUser))
                .addMappings(mapper -> mapper.skip(UserInProjectEntity::getPrivateInfo, UserInProject::setPrivateInfo))

                // custom mappings
                .addMapping(entity -> entity.getId().getProjectId(), UserInProject::setProjectId)
                .addMapping(entity -> entity.getId().getUserId(), UserInProject::setUserId)

                .implicitMappings();
    }

}
