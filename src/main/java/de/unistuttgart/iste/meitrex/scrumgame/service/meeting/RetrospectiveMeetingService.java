package de.unistuttgart.iste.meitrex.scrumgame.service.meeting;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective.RetrospectiveActivityEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective.RetrospectiveMeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.RetrospectiveMeetingRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.UserStatsService;
import de.unistuttgart.iste.meitrex.scrumgame.service.sprint.SprintService;
import de.unistuttgart.iste.meitrex.scrumgame.service.sprint.SprintStatsService;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.UserInProjectService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.function.*;

@Slf4j
@Service
public class RetrospectiveMeetingService
        extends AbstractCrudService<UUID, RetrospectiveMeetingEntity, RetrospectiveMeeting> {

    private static final int GOLD_MEDAL_REWARD   = 100;
    private static final int SILVER_MEDAL_REWARD = 75;
    private static final int BRONZE_MEDAL_REWARD = 50;

    private final MeetingService                 meetingService;
    private final RetrospectiveMeetingRepository repository;
    private final ProjectRepository              projectRepository;
    private final SprintStatsService             sprintStatsService;
    private final SprintService                  sprintService;
    private final UserInProjectService           userInProjectService;
    private final UserStatsService               userStatsService;

    public RetrospectiveMeetingService(
            RetrospectiveMeetingRepository repository,
            ModelMapper modelMapper,
            MeetingService meetingService,
            ProjectRepository projectRepository,
            SprintStatsService sprintStatsService,
            SprintService sprintService,
            UserInProjectService userInProjectService,
            UserStatsService userStatsService
    ) {
        super(repository, modelMapper, RetrospectiveMeetingEntity.class, RetrospectiveMeeting.class);
        this.meetingService = meetingService;
        this.repository = repository;
        this.projectRepository = projectRepository;
        this.sprintStatsService = sprintStatsService;
        this.sprintService = sprintService;
        this.userInProjectService = userInProjectService;
        this.userStatsService = userStatsService;
    }

    public RetrospectiveMeeting createRetrospectiveMeeting(Project project, RetrospectiveMeetingInput input) {
        RetrospectiveMeetingEntity entity = new RetrospectiveMeetingEntity();
        ProjectEntity projectEntity = projectRepository.findByIdOrThrow(project.getId());

        entity.setProject(projectEntity);
        entity.setMeetingType(MeetingType.RETROSPECTIVE);
        entity.setActive(true);
        entity.setCurrentPage(RetrospectiveMeetingPage.INFORMATION);
        entity.setAttendees(meetingService.initMeetingAttendees(input.getMeetingLeaderId()));
        entity.setGoldChallengeReward(Animal.TRICERATOPS); // TODO unlocking
        entity.setBaseRewards(Set.of(KnownAsset.ROCK_1));
        entity.setStreakRewards(Set.of(KnownAsset.FOUNTAIN));
        entity.setMedalsAwarded(false);

        initActivities(entity, input.getActivities());
        initMedals(entity, projectEntity);

        var result = convertToDto(repository.save(entity));

        meetingService.publishMeetingUpdated(result);

        return result;
    }

    @Transactional
    public synchronized RetrospectiveMeeting awardMedals(UUID projectId) {
        RetrospectiveMeetingEntity entity = repository.findFirstByProjectIdAndActive(projectId, true)
                .orElseThrow(() -> new MeitrexNotFoundException("No active retrospective meeting found"));

        if (entity.isMedalsAwarded()) {
            return convertToDto(entity);
        }

        entity.setMedalsAwarded(true);

        updateUserStatsAndBadges(projectId, entity);

        return convertToDto(repository.save(entity));
    }

    @Transactional
    public RetrospectiveMeeting finishMeeting(UUID id) {
        // set end date so that the sprint is not active anymore
        // update sprint number

        RetrospectiveMeetingEntity retrospectiveMeetingEntity = repository.findFirstByProjectIdAndActive(id, true)
                .orElseThrow(() -> new MeitrexNotFoundException("No active retrospective meeting found"));
        retrospectiveMeetingEntity.setActive(false);

        ProjectEntity projectEntity = retrospectiveMeetingEntity.getProject();

        // set end date so that the sprint is not active anymore
        sprintService.updateSprintEntity(projectEntity.getId(),
                projectEntity.getCurrentSprintNumber(),
                sprintEntity -> {
                    sprintEntity.setEndDate(OffsetDateTime.now());
                });

        // update sprint number
        int newSprintNumber = Optional.ofNullable(projectEntity.getCurrentSprintNumber()).orElse(0) + 1;
        projectEntity.setCurrentSprintNumber(newSprintNumber + 1);
        projectRepository.save(projectEntity);

        // TODO unlocks

        RetrospectiveMeeting result = convertToDto(repository.save(retrospectiveMeetingEntity));
        meetingService.publishMeetingUpdated(result);
        meetingService.publishMeetingFinishedEvents(result);
        return result;
    }


    private void updateUserStatsAndBadges(UUID projectId, RetrospectiveMeetingEntity entity) {
        userInProjectService.removeBadges(projectId);

        userInProjectService.addGoldMedalIfPresent(entity.getGoldMedalUserId(), projectId);
        if (entity.getGoldMedalUserId() != null) {
            userStatsService.updateUserStats(entity.getGoldMedalUserId(), projectId, userStats -> {
                userStats.setGoldMedals(userStats.getGoldMedals() + 1);
                userStats.setVirtualCurrency(userStats.getVirtualCurrency() + GOLD_MEDAL_REWARD);
            });
        }

        userInProjectService.addSilverMedalIfPresent(entity.getSilverMedalUserId(), projectId);
        if (entity.getSilverMedalUserId() != null) {
            userStatsService.updateUserStats(entity.getSilverMedalUserId(), projectId, userStats -> {
                userStats.setSilverMedals(userStats.getSilverMedals() + 1);
                userStats.setVirtualCurrency(userStats.getVirtualCurrency() + SILVER_MEDAL_REWARD);
            });
        }

        userInProjectService.addBronzeMedalIfPresent(entity.getBronzeMedalUserId(), projectId);
        if (entity.getBronzeMedalUserId() != null) {
            userStatsService.updateUserStats(entity.getBronzeMedalUserId(), projectId, userStats -> {
                userStats.setBronzeMedals(userStats.getBronzeMedals() + 1);
                userStats.setVirtualCurrency(userStats.getVirtualCurrency() + BRONZE_MEDAL_REWARD);
            });
        }
    }

    private void initActivities(RetrospectiveMeetingEntity entity, List<RetrospectiveActivityInput> activities) {
        log.info("initActivities: " + activities);
        entity.setActivities(activities.stream()
                .map(activityInput -> getModelMapper()
                        .map(activityInput, RetrospectiveActivityEntity.class))
                .peek(activity -> log.info("activity: " + activity))
                .toList());
    }

    public Flux<RetrospectiveMeeting> getRetrospectiveMeetingStream(UUID projectId) {
        return meetingService.getMeetingUpdates(projectId, MeetingType.RETROSPECTIVE, RetrospectiveMeeting.class);
    }

    public Optional<RetrospectiveMeeting> findActiveRetrospectiveMeeting(UUID id) {
        return findActiveRetrospectiveMeetingEntity(id).map(this::convertToDto);
    }

    public Optional<RetrospectiveMeetingEntity> findActiveRetrospectiveMeetingEntity(UUID projectId) {
        return repository.findFirstByProjectIdAndActive(projectId, true);
    }

    public RetrospectiveMeeting updatePage(UUID id, RetrospectiveMeetingPage page) {
        return updateRetrospectiveMeeting(id, retrospectiveMeeting -> retrospectiveMeeting.setCurrentPage(page));
    }

    /**
     * Updates the active retrospective meeting for the given project ID by applying the provided modifier function. If
     * no
     * active retrospective meeting is found for the project ID, a MeitrexNotFoundException is thrown.
     * <p>
     * This method is synchronized to prevent concurrent modifications of the same retrospective meeting.
     * <p>
     * The updated RetrospectiveMeeting is published to the meeting service after applying the modifier function.
     *
     * @param projectId The ID of the project for which the retrospective meeting needs to be updated.
     * @param modifier  The function that modifies the RetrospectiveMeetingEntity.
     * @return The updated RetrospectiveMeeting after applying the modifier function.
     */
    private synchronized RetrospectiveMeeting updateRetrospectiveMeeting(
            UUID projectId,
            Consumer<RetrospectiveMeetingEntity> modifier
    ) {
        RetrospectiveMeetingEntity retrospectiveMeeting = repository.findFirstByProjectIdAndActive(projectId, true)
                .orElseThrow(() -> new MeitrexNotFoundException("No active retrospective meeting found"));

        modifier.accept(retrospectiveMeeting);

        RetrospectiveMeeting result = convertToDto(repository.save(retrospectiveMeeting));

        meetingService.publishMeetingUpdated(result);

        return result;
    }

    private void initMedals(RetrospectiveMeetingEntity entity, ProjectEntity projectEntity) {
        var sprint = sprintService.findSprint(projectEntity.getId(), projectEntity.getCurrentSprintNumber());
        if (sprint.isEmpty()) {
            return;
        }

        var sprintStats = sprintStatsService.getSprintStats(sprint.get());
        List<SprintUserStats> userStats = sprintStats.getUserStats();
        List<SprintUserStats> sortedUserStats = new ArrayList<>(userStats);

        // shuffle to make sure that the order is random if multiple users have the same story points
        Collections.shuffle(sortedUserStats);
        sortedUserStats.sort(Comparator.comparing(SprintUserStats::getStoryPointsCompleted).reversed());

        if (!sortedUserStats.isEmpty()) {
            entity.setGoldMedalUserId(sortedUserStats.getFirst().getUserId());
            entity.setGoldMedalPoints(sortedUserStats.getFirst().getStoryPointsCompleted());
        }
        if (sortedUserStats.size() > 1) {
            entity.setSilverMedalUserId(sortedUserStats.get(1).getUserId());
            entity.setSilverMedalPoints(sortedUserStats.get(1).getStoryPointsCompleted());
        }
        if (sortedUserStats.size() > 2) {
            entity.setBronzeMedalUserId(sortedUserStats.get(2).getUserId());
            entity.setBronzeMedalPoints(sortedUserStats.get(2).getStoryPointsCompleted());
        }
    }
}
