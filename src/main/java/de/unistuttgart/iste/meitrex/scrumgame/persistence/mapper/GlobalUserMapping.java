package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class GlobalUserMapping implements Module {

    @Override
    public void setupModule(ModelMapper modelMapper) {
        modelMapper.createTypeMap(GlobalUserEntity.class, GlobalUser.class)
                .addMapping(GlobalUserEntity::getName, GlobalUser::setUsername);

    }
}
