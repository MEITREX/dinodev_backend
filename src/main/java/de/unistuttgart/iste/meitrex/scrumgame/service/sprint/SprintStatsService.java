package de.unistuttgart.iste.meitrex.scrumgame.service.sprint;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class SprintStatsService {

    private final ImsService imsService;
    private final SprintService sprintService;

    public SprintStats getSprintStats(Sprint sprint) {
        SprintStats sprintStats = new SprintStats();
        sprintStats.setSprint(sprint);

        calculateDateRelatedStats(sprintStats);
        calculateIssueStatistics(sprintStats);
        calculateSprintSuccessState(sprintStats);

        return sprintStats;
    }

    private void calculateSprintSuccessState(SprintStats sprintStats) {
        Optional<Integer> storyPointsPlanned = Optional.ofNullable(sprintStats.getSprint().getStoryPointsPlanned());

        if (storyPointsPlanned.isEmpty()) {
            sprintStats.setSuccessState(SprintSuccessState.UNKNOWN);
            return;
        }

        if (sprintStats.getTotalStoryPoints() >= storyPointsPlanned.get()) {
            Optional<Sprint> previousSprint = sprintService.findSprint(
                    sprintStats.getSprint().getProject().getId(),
                    sprintStats.getSprint().getNumber() - 1);

            if (previousSprint.isEmpty()) {
                sprintStats.setSuccessState(SprintSuccessState.SUCCESS);
                return;
            }

            // TODO this could be optimized by storing the sprint stats in the database or caching them
            SprintStats previousSprintStats = getSprintStats(previousSprint.get());

            if (sprintStats.getTotalStoryPoints() > previousSprintStats.getTotalStoryPoints()) {
                sprintStats.setSuccessState(SprintSuccessState.SUCCESS_WITH_GOLD_CHALLENGE);
                return;
            }

            sprintStats.setSuccessState(SprintSuccessState.SUCCESS);
        } else if (sprintStats.getSprint().getEndDate() == null
                   || OffsetDateTime.now().isAfter(sprintStats.getSprint().getEndDate())) {
            sprintStats.setSuccessState(SprintSuccessState.FAILED);
        } else {
            sprintStats.setSuccessState(SprintSuccessState.IN_PROGRESS);
        }
    }

    private void calculateIssueStatistics(SprintStats sprintStats) {
        List<Issue> issues = imsService.getIssuesBySprints(List.of(sprintStats.getSprint()))
                .get(sprintStats.getSprint());
        sprintStats.setIssueCount(issues.size());

        IntSummaryStatistics stats = issues.stream()
                .filter(issue -> issue.getStoryPoints() != null)
                .filter(issue -> issue.getState().getType() == IssueStateType.DONE
                                 || issue.getState().getType() == IssueStateType.DONE_SPRINT)
                .mapToInt(Issue::getStoryPoints)
                .summaryStatistics();
        int totalStoryPoints = (int) stats.getSum();
        sprintStats.setTotalStoryPoints(totalStoryPoints);
        sprintStats.setAverageStoryPoints(stats.getAverage());

        calculatePercentages(issues, sprintStats, totalStoryPoints);
    }

    private void calculateDateRelatedStats(SprintStats sprintStats) {
        Sprint sprint = sprintStats.getSprint();
        sprintStats.setDaysElapsed(calculateDaysElapsed(sprint));
        sprintStats.setDaysLeft(calculateDaysLeft(sprint));
        sprintStats.setPercentageTimeElapsed(calculatePercentageTimeElapsed(sprint));
    }

    private void calculatePercentages(List<Issue> issues, SprintStats sprintStats, int totalStoryPoints) {
        Map<IssueStateType, Integer> storyPointsByState = issues.stream()
                .filter(issue -> issue.getStoryPoints() != null && issue.getState() != null)
                .collect(Collectors.groupingBy(issue -> issue.getState().getType(),
                        Collectors.summingInt(Issue::getStoryPoints)));

        Optional<Integer> plannedStoryPoints = Optional.ofNullable(sprintStats.getSprint().getStoryPointsPlanned());

        sprintStats.setPercentageStoryPointsCompleted(getPercentage(storyPointsByState,
                plannedStoryPoints.orElse(0),
                IssueStateType.DONE,
                IssueStateType.DONE_SPRINT));
        sprintStats.setPercentageStoryPointsInProgress(getPercentage(storyPointsByState,
                plannedStoryPoints.orElse(0),
                IssueStateType.IN_PROGRESS));
        sprintStats.setPercentageStoryPointsNotStarted(getPercentage(storyPointsByState,
                plannedStoryPoints.orElse(0),
                IssueStateType.SPRINT_BACKLOG));
    }

    private double getPercentage(Map<IssueStateType, Integer> storyPointsByState,
            int totalStoryPoints,
            IssueStateType... types) {
        if (totalStoryPoints == 0) {
            return 0;
        }

        int storyPoints = Arrays.stream(types)
                .mapToInt(type -> storyPointsByState.getOrDefault(type, 0))
                .sum();
        return (double) storyPoints / totalStoryPoints * 100;
    }

    private int calculateDaysElapsed(Sprint sprint) {
        if (sprint.getStartDate() == null) {
            return 0;
        }
        return (int) Duration.between(sprint.getStartDate(), OffsetDateTime.now()).toDays();
    }

    private int calculateDaysLeft(Sprint sprint) {
        if (sprint.getEndDate() == null) {
            return 0;
        }
        return (int) Duration.between(OffsetDateTime.now(), sprint.getEndDate()).toDays();
    }

    private double calculatePercentageTimeElapsed(Sprint sprint) {
        if (sprint.getStartDate() == null || sprint.getEndDate() == null) {
            return 100;
        }
        Duration sprintDuration = Duration.between(sprint.getStartDate(), sprint.getEndDate());
        Duration timeElapsed = Duration.between(sprint.getStartDate(), OffsetDateTime.now());
        return (double) timeElapsed.toMillis() / sprintDuration.toMillis() * 100;
    }
}
