package de.unistuttgart.iste.meitrex.scrumgame.service.meeting;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning.PlanningMeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning.PlanningSettingsEmbeddable;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.PlanningMeetingRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsService;
import de.unistuttgart.iste.meitrex.scrumgame.service.project.ProjectService;
import de.unistuttgart.iste.meitrex.scrumgame.service.sprint.SprintService;
import de.unistuttgart.iste.meitrex.scrumgame.util.OrdinalScaleStats;
import de.unistuttgart.iste.meitrex.scrumgame.util.TShirtSizeEstimationStoryPointsConverter;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Service for handling planning meetings.
 * <p>
 * Remark: This service has many dependencies. This is because the planning meeting modify issues, projects,
 * can create sprints etc. This might be considered as a code smell but refactoring
 * this service into smaller services might not necessarily lead to a better design, as this class bundles all
 * the logic for planning meetings.
 */
@Slf4j
@Service
@Getter(AccessLevel.PROTECTED)
public class PlanningMeetingService extends AbstractCrudService<UUID, PlanningMeetingEntity, PlanningMeeting> {

    private final PlanningMeetingRepository planningMeetingRepository;
    private final MeetingService            meetingService;
    private final AuthService   auth;
    private final ImsService    imsService;
    private final SprintService sprintService;
    private final ProjectService            projectService;

    public PlanningMeetingService(PlanningMeetingRepository repository,
            ModelMapper modelMapper,
            MeetingService meetingService,
            AuthService auth,
            ImsService imsService,
            SprintService sprintService,
            ProjectService projectService
    ) {
        super(repository, modelMapper, PlanningMeetingEntity.class, PlanningMeeting.class);
        this.planningMeetingRepository = repository;
        this.meetingService = meetingService;
        this.auth = auth;
        this.imsService = imsService;
        this.sprintService = sprintService;
        this.projectService = projectService;
    }

    public PlanningMeeting createPlanningMeeting(Project project, PlanningMeetingInput input) {
        PlanningMeetingEntity planningMeeting = new PlanningMeetingEntity();

        planningMeeting.setPlanningSettings(
                getModelMapper().map(input.getPlanningSettings(), PlanningSettingsEmbeddable.class));
        planningMeeting.setMeetingType(MeetingType.PLANNING);
        planningMeeting.setProject(projectService.getProjectEntity(project.getId()));
        planningMeeting.setAttendees(meetingService.initMeetingAttendees(input.getMeetingLeaderId()));
        planningMeeting.setCurrentPage(PlanningMeetingPage.INFORMATION);
        planningMeeting.setActive(true);
        planningMeeting.getAnimalVoting()
                .setVotableAnimals(new ArrayList<>(
                        List.of(Animal.DODO, Animal.TREX)
                )); // TODO only unlocked animals

        initSprintGoalVoting(planningMeeting, project);

        var result = convertToDto(planningMeetingRepository.save(planningMeeting));

        meetingService.publishMeetingUpdated(result);

        return result;
    }

    private void initSprintGoalVoting(PlanningMeetingEntity planningMeeting, Project project) {
        List<Issue> issuesOfProject = imsService.getIssues(project);
        planningMeeting.getSprintGoalVoting().getNonSprintIssueIds().addAll(
                issuesOfProject.stream()
                        .filter(issue -> issue.getState().getType() == IssueStateType.BACKLOG)
                        .map(Issue::getId)
                        .toList());
        planningMeeting.getSprintGoalVoting().getSprintIssueIds().addAll(
                issuesOfProject.stream()
                        .filter(issue -> issue.getState().getType() == IssueStateType.SPRINT_BACKLOG)
                        .map(Issue::getId)
                        .toList());
    }

    public Optional<PlanningMeeting> findActivePlanningMeeting(UUID projectId) {
        return findActivePlanningMeetingEntity(projectId).map(this::convertToDto);
    }

    public Optional<PlanningMeetingEntity> findActivePlanningMeetingEntity(UUID projectId) {
        return planningMeetingRepository.findFirstByProjectIdAndActive(projectId, true);
    }

    public PlanningMeetingMutation mutatePlanningMeeting(Project project) {
        return PlanningMeetingMutation.builder().setProject(project).build();
    }

    public PlanningMeeting changePage(UUID projectId, PlanningMeetingPage page) {
        return updatePlanningMeeting(projectId, planningMeeting -> planningMeeting.setCurrentPage(page));
    }

    public PlanningMeeting voteAnimal(UUID projectId, Animal animal) {
        return updatePlanningMeeting(projectId, planningMeeting ->
                planningMeeting.getAnimalVoting().addVote(auth.getCurrentUserId(), animal));
    }

    public PlanningMeeting endAnimalVoting(UUID projectId) {
        return updatePlanningMeeting(projectId, planningMeeting -> planningMeeting.getAnimalVoting().finishVoting());
    }

    public PlanningMeeting addName(UUID projectId, String name) {
        return updatePlanningMeeting(projectId, planningMeeting ->
                planningMeeting.getNameVoting().getVotableNames().add(name));
    }

    public PlanningMeeting voteName(UUID projectId, String name) {
        return updatePlanningMeeting(projectId, planningMeeting ->
                planningMeeting.getNameVoting().addVote(auth.getCurrentUserId(), name));
    }

    public PlanningMeeting endNameVoting(UUID projectId) {
        return updatePlanningMeeting(projectId, planningMeeting -> planningMeeting.getNameVoting().finishVoting());
    }

    public PlanningMeeting voteEstimation(UUID projectId, TShirtSizeEstimation estimation) {
        return updatePlanningMeeting(projectId, planningMeeting ->
                planningMeeting.getIssueEstimation().addVote(auth.getCurrentUserId(), estimation));
    }

    public PlanningMeeting restartEstimation(UUID projectId) {
        return updatePlanningMeeting(projectId, planningMeeting -> planningMeeting.getIssueEstimation().resetVotes());
    }

    public PlanningMeeting nextIssue(UUID projectId, String issueId) {
        return updatePlanningMeeting(projectId, planningMeeting -> {
            planningMeeting.getIssueEstimation().resetVotes();
            planningMeeting.getIssueEstimation().setIssueId(issueId);
        });
    }

    public PlanningMeeting startCountdown(UUID projectId, int seconds) {
        return updatePlanningMeeting(projectId, planningMeeting -> {
            planningMeeting.getIssueEstimation().setCountdownSeconds(seconds);
            // finish voting in x seconds:
            Mono.delay(Duration.ofSeconds(seconds))
                    .doOnTerminate(() -> endEstimation(projectId))
                    .subscribe();
        });
    }

    public PlanningMeeting endEstimation(UUID projectId) {
        return updatePlanningMeeting(projectId, planningMeeting ->
                planningMeeting.getIssueEstimation().finishVoting());
    }

    public PlanningMeeting setFinalResult(Project project, TShirtSizeEstimation estimation) {
        return updatePlanningMeeting(project.getId(), planningMeeting -> {
            IssueMutation issueMutation = imsService.mutateIssue(project,
                    planningMeeting.getIssueEstimation().getIssueId());
            imsService.changeIssueEstimation(issueMutation, estimation);

            planningMeeting.getIssueEstimation().finishVoting();
            planningMeeting.getIssueEstimation().setFinalResult(estimation);
            planningMeeting.getIssueEstimation().getVotes().clear();
            planningMeeting.getIssueEstimation().setIssueId(null);
        });
    }

    public PlanningMeeting addSprintIssue(UUID projectId, String issueId) {
        return updatePlanningMeeting(projectId, planningMeeting -> {
            planningMeeting.getSprintGoalVoting().getSprintIssueIds().add(issueId);
            planningMeeting.getSprintGoalVoting().getNonSprintIssueIds().remove(issueId);
        });
    }

    public PlanningMeeting removeSprintIssue(UUID projectId, String issueId) {
        return updatePlanningMeeting(projectId, planningMeeting -> {
            planningMeeting.getSprintGoalVoting().getSprintIssueIds().remove(issueId);
            planningMeeting.getSprintGoalVoting().getNonSprintIssueIds().add(issueId);
        });
    }

    public PlanningMeeting finishSprintGoalVoting(UUID projectId) {
        return updatePlanningMeeting(projectId, planningMeeting ->
                planningMeeting.getSprintGoalVoting().finishVoting());
    }

    /**
     * Finishes the planning meeting and creates a new sprint based on the planning meeting results.
     * <p>
     * The planning meeting is updated to be inactive and the sprint goal issues are moved to the sprint backlog.
     * A new sprint is created with the sprint goal, animal, name, and start and end date based on the planning
     * meeting results.
     *
     * @param projectId The ID of the project for which to finish the planning meeting.
     * @return The newly created sprint.
     * @throws MeitrexNotFoundException If no active planning meeting is found for the project ID.
     */
    @Transactional
    public Sprint finishMeeting(UUID projectId) {
        PlanningMeeting planningMeeting = updatePlanningMeeting(projectId, meetingEntity ->
                meetingEntity.setActive(false));

        setStateAndSprintNumberForPlannedIssues(projectId, planningMeeting);

        CreateSprintInput input = getCreateSprintInputFromMeeting(projectId, planningMeeting);

        meetingService.publishMeetingFinishedEvents(planningMeeting);

        return sprintService.createNewSprint(projectId, input);
    }

    private CreateSprintInput getCreateSprintInputFromMeeting(UUID projectId, PlanningMeeting planningMeeting) {
        return CreateSprintInput.builder()
                .setAnimal(planningMeeting.getAnimalVoting().getVotingResult())
                .setName(planningMeeting.getNameVoting().getVotingResult())
                .setStartDate(planningMeeting.getPlanningSettings().getSprintStartDate())
                .setEndDate(planningMeeting.getPlanningSettings().getSprintStartDate()
                        .plusDays(planningMeeting.getPlanningSettings().getSprintDurationDays()))
                .setStoryPointsPlanned(getTotalStoryPointsPlanned(projectId, planningMeeting))
                .setCustomGoldChallengeReward(planningMeeting.getPlanningSettings().getCustomGoldChallengeReward())
                .build();
    }

    private void setStateAndSprintNumberForPlannedIssues(UUID projectId, PlanningMeeting planningMeeting) {
        Project project = projectService.getProjectOrThrow(projectId);

        for (String issueId : planningMeeting.getSprintGoalVoting().getSprintIssueIds()) {
            IssueMutation issueMutation = imsService.mutateIssue(project, issueId);
            imsService.changeIssueState(issueMutation, IssueStateType.SPRINT_BACKLOG);
            imsService.changeSprint(issueMutation, project.getCurrentSprintNumber() + 1);
        }
    }

    private int getTotalStoryPointsPlanned(UUID projectId, PlanningMeeting planningMeeting) {
        List<Issue> plannedIssues = imsService.getIssuesByIds(projectId,
                planningMeeting.getSprintGoalVoting().getSprintIssueIds());

        return plannedIssues.stream()
                .mapToInt(issue -> TShirtSizeEstimationStoryPointsConverter.getStoryPoints(issue.getEffortEstimation()))
                .sum();
    }

    /**
     * Updates the active planning meeting for the given project ID by applying the provided modifier function. If no
     * active planning meeting is found for the project ID, a MeitrexNotFoundException is thrown.
     * <p>
     * This method is synchronized to prevent concurrent modifications of the same planning meeting.
     * <p>
     * The updated PlanningMeeting is published to the meeting service after applying the modifier function.
     *
     * @param projectId The ID of the project for which the planning meeting needs to be updated.
     * @param modifier  The function that modifies the PlanningMeetingEntity based on the business logic.
     * @return The updated PlanningMeeting after applying the modifier function.
     * @throws MeitrexNotFoundException If no active planning meeting is found for the project ID.
     */
    private synchronized PlanningMeeting updatePlanningMeeting(
            UUID projectId,
            Consumer<PlanningMeetingEntity> modifier
    ) {
        PlanningMeetingEntity planningMeeting = planningMeetingRepository.findFirstByProjectIdAndActive(projectId, true)
                .orElseThrow(() -> new MeitrexNotFoundException("No active planning meeting found"));

        modifier.accept(planningMeeting);

        PlanningMeeting result = convertToDto(planningMeetingRepository.save(planningMeeting));

        meetingService.publishMeetingUpdated(result);

        return result;
    }

    public Optional<EstimationStats> calculateEstimationStats(IssueEstimation issueEstimation) {
        if (!issueEstimation.getFinished() || issueEstimation.getVotes().isEmpty()) {
            return Optional.empty();
        }

        Map<TShirtSizeEstimation, Long> estimationCounts = issueEstimation.getVotes()
                .stream()
                .collect(Collectors.toMap(
                        EstimationVote::getVotedFor,
                        estimationVote -> (long) estimationVote.getUserVotes().size()));

        OrdinalScaleStats<TShirtSizeEstimation> stats = OrdinalScaleStats.calculate(estimationCounts);

        return Optional.of(EstimationStats.builder()
                .setMin(stats.getMin())
                .setMax(stats.getMax())
                .setMode(stats.getMode())
                .setMedian(stats.getMedian())
                .build());
    }

    public Flux<PlanningMeeting> getPlanningMeetingUpdatedSubscription(UUID projectId) {
        return meetingService.getMeetingUpdates(projectId, MeetingType.PLANNING, PlanningMeeting.class);
    }

}
