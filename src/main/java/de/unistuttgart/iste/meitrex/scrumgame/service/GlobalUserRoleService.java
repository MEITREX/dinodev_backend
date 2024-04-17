package de.unistuttgart.iste.meitrex.scrumgame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUserRole;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalUserRoleService {

    private final GlobalUserRoleRepository globalUserRoleRepository;
    private final ObjectMapper objectMapper;

    public List<GlobalUserRole> getAllGlobalUserRoles() {
        List<GlobalUserRoleEntity> globalUserRoleEntities = globalUserRoleRepository.findAll();

        return globalUserRoleEntities.stream()
                .map(this::convertToDto)
                .toList();
    }

    public GlobalUserRole getGlobalUserRole(String roleName) {
        GlobalUserRoleEntity roleEntity = globalUserRoleRepository.findByIdOrThrow(roleName);
        return convertToDto(roleEntity);
    }

    private GlobalUserRole convertToDto(GlobalUserRoleEntity roleEntity) {
        return objectMapper.convertValue(roleEntity, GlobalUserRole.class);
    }
}
