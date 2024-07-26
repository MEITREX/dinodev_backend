package de.unistuttgart.iste.meitrex.scrumgame.service.gamification;

import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.AchievementProgress;
import de.unistuttgart.iste.meitrex.generated.dto.AllowedDataType;
import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.DataFieldInput;
import de.unistuttgart.iste.meitrex.rulesengine.util.DefaultEventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementProgressEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementProgressEntity.AchievementProgressId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.AchievementProgressRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.AchievementRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.ScrumGameEventTypes;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for managing achievements and their progress.
 */
@Service
public class AchievementService
        extends AbstractCrudService<AchievementProgressId, AchievementProgressEntity, AchievementProgress> {

    private final AchievementProgressRepository achievementProgressRepository;
    private final AchievementRepository         achievementRepository;
    private final DefaultEventPublisher         eventPublisher;

    public AchievementService(
            AchievementProgressRepository repository,
            AchievementRepository achievementRepository,
            DefaultEventPublisher eventPublisher,
            ModelMapper modelMapper
    ) {
        super(repository, modelMapper, AchievementProgressEntity.class, AchievementProgress.class);
        this.achievementProgressRepository = repository;
        this.achievementRepository = achievementRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<AchievementProgress> getForUserInProject(UUID userId, UUID projectId) {
        List<AchievementEntity> achievements = achievementRepository.findAll();
        UserProjectId userProjectId = new UserProjectId(userId, projectId);

        return achievements.stream()
                .map(achievement -> findOrInitAchievementProgressEntity(userProjectId, achievement))
                .map(this::convertToDto)
                .toList();
    }

    public AchievementProgress logAchievementProgress(
            UserProjectId userProjectId,
            String achievementId,
            int progressToAdd
    ) {
        AchievementEntity achievement = achievementRepository.findByIdOrThrow(achievementId);

        AchievementProgressEntity achievementProgressEntity
                = findOrInitAchievementProgressEntity(userProjectId, achievement);

        int progressBefore = achievementProgressEntity.getProgress();
        achievementProgressEntity.setProgress(progressBefore + progressToAdd);

        if (achievementProgressEntity.getProgress() >= achievement.getGoal() &&
            progressBefore < achievement.getGoal()) {
            publishAchievementEvent(userProjectId, achievement);
        }

        return convertToDto(achievementProgressRepository.save(achievementProgressEntity));
    }

    @PreAuthorize("@auth.hasPrivilege(@globalPrivileges.UPDATE_USER)")
    @Transactional
    public void resetAchievements(UUID projectId) {
        achievementProgressRepository.deleteAllByIdProjectId(projectId);
    }

    @PostConstruct
    public void saveDefaultAchievements() {
        achievementRepository.saveAll(DefaultAchievements.DEFAULT_ACHIEVEMENTS);
    }

    private AchievementProgressEntity findOrInitAchievementProgressEntity(
            UserProjectId userProjectId,
            AchievementEntity achievement) {

        AchievementProgressId achievementProgressId = new AchievementProgressId(achievement.getIdentifier(),
                userProjectId.getProjectId(), userProjectId.getUserId());

        return achievementProgressRepository
                .findById(achievementProgressId)
                .orElseGet(() -> AchievementProgressEntity.builder()
                        .setId(achievementProgressId)
                        .setAchievement(achievement)
                        .setProgress(0)
                        .build());
    }

    private void publishAchievementEvent(UserProjectId userProjectId, AchievementEntity achievement) {
        eventPublisher.publishEvent(
                CreateEventInput.builder()
                        .setUserId(userProjectId.getUserId())
                        .setProjectId(userProjectId.getProjectId())
                        .setEventData(List.of(DataFieldInput.builder()
                                .setKey("achievementName")
                                .setType(AllowedDataType.STRING)
                                .setValue(achievement.getName())
                                .build()))
                        .setEventTypeIdentifier(ScrumGameEventTypes.ACHIEVEMENT_UNLOCKED.getIdentifier())
                        .build()
        );
    }
}
