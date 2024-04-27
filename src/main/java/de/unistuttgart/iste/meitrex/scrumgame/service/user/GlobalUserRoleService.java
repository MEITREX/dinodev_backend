package de.unistuttgart.iste.meitrex.scrumgame.service.user;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserRoleInput;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUserRole;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserRoleInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service for managing global user roles.
 */
@Service
@RequiredArgsConstructor
public class GlobalUserRoleService extends AbstractCrudService<String, GlobalUserRoleEntity, GlobalUserRole> {

    private final GlobalUserRoleRepository globalUserRoleRepository;
    private final ModelMapper modelMapper;

    public List<GlobalUserRole> getAllGlobalUserRoles() {
        return getAll();
    }

    public Optional<GlobalUserRole> findGlobalUserRole(String name) {
        return find(name);
    }

    @PreAuthorize("@auth.hasPrivilege(@globalPrivileges.CREATE_ROLE)")
    public GlobalUserRole createGlobalUserRole(CreateGlobalUserRoleInput input) {
        globalUserRoleRepository.requireNotExists(input.getName());
        return create(input);
    }

    @PreAuthorize("@auth.hasPrivilege(@globalPrivileges.CHANGE_ROLES) and #name != 'ADMIN'")
    public GlobalUserRole updateGlobalUserRole(String name, UpdateGlobalUserRoleInput input) {
        return update(name, entity -> entity.setGlobalPrivileges(input.getGlobalPrivileges()));
    }

    @PreAuthorize("@auth.hasPrivilege(@globalPrivileges.DELETE_ROLE)")
    public boolean deleteGlobalUserRole(String name) {
        return delete(name);
    }

    /**
     * Get the global user role entity by its name.
     *
     * @param roleName the name of the role
     * @return the global user role entity
     * @throws NoSuchElementException if the role does not exist
     */
    public GlobalUserRoleEntity getGlobalUserRoleEntity(String roleName) {
        return globalUserRoleRepository.findByIdOrThrow(roleName);
    }

    /**
     * Get or create the admin role. The admin role has all global privileges.
     *
     * @return the admin role
     */
    public GlobalUserRoleEntity getOrCreateAdminRole() {
        return globalUserRoleRepository.save(GlobalUserRoleEntity.builder()
                .name("ADMIN")
                // add all global privileges to the admin role
                .globalPrivileges(Arrays.asList(GlobalPrivilege.values()))
                .build());
    }

    @Override
    protected Class<GlobalUserRoleEntity> getEntityClass() {
        return GlobalUserRoleEntity.class;
    }

    @Override
    protected Class<GlobalUserRole> getDtoClass() {
        return GlobalUserRole.class;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    protected MeitrexRepository<GlobalUserRoleEntity, String> getRepository() {
        return globalUserRoleRepository;
    }

}
