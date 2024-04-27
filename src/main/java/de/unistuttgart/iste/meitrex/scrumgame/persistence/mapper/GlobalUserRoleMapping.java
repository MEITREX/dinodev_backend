package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserRoleInput;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUserRole;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class GlobalUserRoleMapping implements Module {
    @Override
    public void setupModule(ModelMapper modelMapper) {
        modelMapper.createTypeMap(GlobalUserRoleEntity.class, GlobalUserRole.class);
        // no special mappings needed

        modelMapper.createTypeMap(CreateGlobalUserRoleInput.class, GlobalUserRoleEntity.class);
        // no special mappings needed

        // no mapping for UpdateGlobalUserRoleInput -> GlobalUserRoleEntity needed
    }
}
