package de.unistuttgart.iste.meitrex.scrumgame.service;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRoleInProjectService {
    public List<UserRoleInProject> getAllRoles(UUID id) {
        return List.of();
    }

    public UserRoleInProject getRole(UUID projectId, UUID roleId) {
        return UserRoleInProject.builder().setId(UUID.randomUUID()).build();
    }

    public UserRoleInProject createRole(UUID id, CreateRoleInput input) {
        return getRole(UUID.randomUUID(), UUID.randomUUID());
    }


    public UserRoleInProject updateRole(UUID projectMutationId, UUID roleId, UpdateRoleInput input) {
        return getRole(UUID.randomUUID(), UUID.randomUUID());
    }


    public boolean deleteRole(UUID id, UUID id1) {
        return false;
    }
}
