package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class GlobalUserMapping implements Module {

    @Override
    public void setupModule(ModelMapper modelMapper) {
        /* GlobalUserEntity -> GlobalUser */
        modelMapper.createTypeMap(GlobalUserEntity.class, GlobalUser.class);
        // no mappings needed

        /* UpdateGlobalUserInput -> GlobalUserEntity */
        modelMapper.emptyTypeMap(UpdateGlobalUserInput.class, GlobalUserEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(GlobalUserEntity::setId);
                    mapper.skip(GlobalUserEntity::setRoles);
                })

                .addMapping(UpdateGlobalUserInput::getUsername, GlobalUserEntity::setUsername)

                .implicitMappings();

        /* CreateGlobalUserInput -> GlobalUserEntity not needed */
    }
}
