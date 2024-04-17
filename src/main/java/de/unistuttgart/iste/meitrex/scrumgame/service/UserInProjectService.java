package de.unistuttgart.iste.meitrex.scrumgame.service;

import de.unistuttgart.iste.meitrex.generated.dto.PrivateUserInfo;
import de.unistuttgart.iste.meitrex.generated.dto.UserInProject;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserInProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserInProjectService {

    private final UserInProjectRepository userInProjectRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;

    public Optional<UserInProject> getUserInProject(UUID userId, UUID projectId) {
        return userInProjectRepository
                .findById(new UserProjectId(userId, projectId))
                .map(this::convertToDto);
    }

    public List<UserInProject> getUsersInProject(UUID projectId) {
        List<UserInProjectEntity> userInProjectEntities = userInProjectRepository.findAllByIdProjectId(projectId);
        return convertToDtos(userInProjectEntities);
    }

    public Optional<UserInProject> getCurrentUserInProject(UUID projectId) {
        UUID currentUserId = authService.getCurrentUserId();

        return getUserInProject(currentUserId, projectId);
    }

    @PreAuthorize("@auth.currentUserId.equals(#userId)")
    public PrivateUserInfo getPrivateInfo(UUID userId, UUID projectId) {
        PrivateUserInfoEmbeddable privateInfo = userInProjectRepository
                .findByIdOrThrow(new UserProjectId(userId, projectId))
                .getPrivateInfo();

        return modelMapper.map(privateInfo, PrivateUserInfo.class);
    }

    private UserInProject convertToDto(UserInProjectEntity userRoleInProjectEntity) {
        return modelMapper.map(userRoleInProjectEntity, UserInProject.class);
    }

    private List<UserInProject> convertToDtos(List<UserInProjectEntity> userRoleInProjectEntities) {
        return userRoleInProjectEntities.stream()
                .map(this::convertToDto)
                .toList();
    }

}
