package de.unistuttgart.iste.meitrex.scrumgame.service.gamification;

import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.UserStats;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserStatsEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserStatsRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.*;

@Service
public class UserStatsService extends AbstractCrudService<UserProjectId, UserStatsEntity, UserStats> {

    private final UserStatsRepository userStatsRepository;

    public UserStatsService(UserStatsRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, UserStatsEntity.class, UserStats.class);
        this.userStatsRepository = repository;
    }

    public UserStats findOrInitUserStats(UUID userId, UUID projectId) {
        return convertToDto(findOrInitUserStatsEntity(userId, projectId));
    }

    public Optional<UserStatsEntity> findUserStatsEntity(UUID userId, UUID projectId) {
        return userStatsRepository.findById(new UserProjectId(userId, projectId));
    }

    public UserStatsEntity findOrInitUserStatsEntity(UUID userId, UUID projectId) {
        return userStatsRepository.findById(new UserProjectId(userId, projectId))
                .orElseGet(() -> UserStatsEntity.builder().id(new UserProjectId(userId, projectId)).build());
    }

    public synchronized UserStats updateUserStats(
            UUID userId,
            UUID projectId,
            Consumer<UserStatsEntity> userStatsUpdater
    ) {
        UserStatsEntity userStats = findOrInitUserStatsEntity(userId, projectId);
        userStatsUpdater.accept(userStats);
        return convertToDto(userStatsRepository.save(userStats));
    }

    // TODO replace with more fitting project role
    @PreAuthorize("@auth.hasPrivilege(@globalPrivileges.UPDATE_USER)")
    @Transactional
    public void resetUserStatsInProject(UUID projectId) {
        userStatsRepository.deleteAllByIdProjectId(projectId);
    }
}
