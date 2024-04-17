package de.unistuttgart.iste.meitrex.scrumgame.service;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GlobalUserService {

    private final AuthService auth;

    private final GlobalUserRepository globalUserRepository;
    private final GlobalUserRoleRepository globalUserRoleRepository;

    private final ModelMapper modelMapper;

    @PreAuthorize("@auth.hasPrivilege(@globalPrivileges.LIST_USERS)")
    public List<GlobalUser> getAllGlobalUsers() {
        List<GlobalUserEntity> globalUserEntities = globalUserRepository.findAll();

        return convertToDtos(globalUserEntities);
    }

    // no specific authorization requirements
    public Optional<GlobalUser> getGlobalUser(UUID userId) {
        return globalUserRepository
                .findById(userId)
                .map(this::convertToDto);
    }

    public GlobalUser getGlobalUserOrThrow(UUID userId) {
        GlobalUserEntity globalUserEntity = globalUserRepository.findByIdOrThrow(userId);

        return convertToDto(globalUserEntity);
    }

    public GlobalUser grantRole(UUID userId, String roleName) {
        GlobalUserRoleEntity roleEntity = globalUserRoleRepository.findByIdOrThrow(roleName);
        GlobalUserEntity userEntity = globalUserRepository.findByIdOrThrow(userId);

        userEntity.getRoles().add(roleEntity);

        GlobalUserEntity savedEntity = globalUserRepository.save(userEntity);

        return convertToDto(savedEntity);
    }

    // no specific authorization requirements
    public GlobalUser registerNewUser(CreateGlobalUserInput input) {
        globalUserRepository.requireNotExists(auth.getCurrentUserId());

        GlobalUserEntity newUserEntity = GlobalUserEntity.builder()
                .id(auth.getCurrentUserId())
                .name(input.getUsername())
                .avatar(input.getAvatar())
                .roles(List.of())
                .build();

        GlobalUserEntity savedEntity = globalUserRepository.save(newUserEntity);

        return convertToDto(savedEntity);
    }

    @PreAuthorize("@auth.currentUserId().equals(#id) || @auth.hasPrivilege(@globalPrivileges.UPDATE_USER)")
    public GlobalUser updateGlobalUser(UUID id, UpdateGlobalUserInput input) {
        GlobalUserEntity globalUserEntity = globalUserRepository.findByIdOrThrow(id);

        globalUserEntity.setName(input.getUsername());
        globalUserEntity.setAvatar(input.getAvatar());

        GlobalUserEntity savedEntity = globalUserRepository.save(globalUserEntity);

        return convertToDto(savedEntity);
    }

    // no specific authorization requirements
    public Optional<GlobalUser> getCurrentUser() {
        return getGlobalUser(auth.getCurrentUserId());
    }

    private GlobalUser convertToDto(GlobalUserEntity globalUserEntity) {
        return modelMapper.map(globalUserEntity, GlobalUser.class);
    }

    private List<GlobalUser> convertToDtos(List<GlobalUserEntity> globalUserEntities) {
        return globalUserEntities.stream()
                .map(this::convertToDto)
                .toList();
    }
}
