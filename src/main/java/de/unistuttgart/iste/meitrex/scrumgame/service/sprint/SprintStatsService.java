package de.unistuttgart.iste.meitrex.scrumgame.service.sprint;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.EventService;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsServiceExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class SprintStatsService {

    private final ImsServiceExtension imsServiceExtension;
    private final SprintService       sprintService;
    private final EventService eventService;

    // cache for sprint stats, does not include the current sprint
    private final Map<Sprint, SprintStats> sprintStatsCache = new ConcurrentHashMap<>();

    public SprintStats getSprintStats(Sprint sprint) {
        if (sprintStatsCache.containsKey(sprint)) {
            return sprintStatsCache.get(sprint);
        }

        SprintStats sprintStats = new SprintStats();
        sprintStats.setSprint(sprint);

        calculateDateRelatedStats(sprintStats);
        calculateIssueStatistics(sprintStats);
        calculateSprintSuccessStateAndStreak(sprintStats);

        if (canCache(sprintStats)) {
            sprintStatsCache.put(sprint, sprintStats);
        }

        return sprintStats;
    }

    public SprintUserStats getUserStatsByUserId(SprintStats stats, UUID userId) {
        return stats.getUserStats().stream()
                .filter(userStats -> userStats.getUserId().equals(userId))
                .findFirst()
                .orElse(new SprintUserStats(userId, 0));
    }

    private boolean canCache(SprintStats sprintStats) {
        return sprintStats.getSuccessState() != SprintSuccessState.IN_PROGRESS
               && (sprintStats.getSprint().getEndDate() == null
                   || OffsetDateTime.now().isAfter(sprintStats.getSprint().getEndDate()));
    }

    private void calculateSprintSuccessStateAndStreak(SprintStats sprintStats) {
        Optional<SprintStats> previousSprintStats = sprintService.findSprint(
                        sprintStats.getSprint().getProject().getId(),
                        sprintStats.getSprint().getNumber() - 1)
                .map(this::getSprintStats);

        calculateSprintSuccessState(sprintStats, previousSprintStats);
        calculateSprintStreak(sprintStats, previousSprintStats);
    }

    private void calculateSprintSuccessState(SprintStats sprintStats, Optional<SprintStats> previousSprintStats) {
        Optional<Integer> storyPointsPlanned = Optional.ofNullable(sprintStats.getSprint().getStoryPointsPlanned());

        if (storyPointsPlanned.isEmpty()) {
            // for sprints without planned story points, we cannot determine the success state and streak
            sprintStats.setSuccessState(SprintSuccessState.UNKNOWN);
            return;
        }

        if (sprintStats.getTotalStoryPoints() >= storyPointsPlanned.get()) {
            if (previousSprintStats.isEmpty()) {
                sprintStats.setSuccessState(SprintSuccessState.SUCCESS);
                return;
            }

            Integer previousStoryPointsPlanned = previousSprintStats.get().getSprint().getStoryPointsPlanned();

            if ((previousStoryPointsPlanned != null &&
                 sprintStats.getTotalStoryPoints() > previousStoryPointsPlanned)
                || sprintStats.getTotalStoryPoints() > previousSprintStats.get().getTotalStoryPoints()
            ) {
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

    private void calculateSprintStreak(SprintStats sprintStats, Optional<SprintStats> previousSprintStats) {
        int previousStreak = previousSprintStats.map(SprintStats::getStreak).orElse(0);

        switch (sprintStats.getSuccessState()) {
            case SUCCESS, SUCCESS_WITH_GOLD_CHALLENGE -> sprintStats.setStreak(previousStreak + 1);
            case FAILED -> sprintStats.setStreak(0);
            default -> sprintStats.setStreak(previousStreak);
        }
    }

    private void calculateIssueStatistics(SprintStats sprintStats) {
        List<Issue> issues = imsServiceExtension.getIssuesBySprints(List.of(sprintStats.getSprint()))
                .get(sprintStats.getSprint());
        sprintStats.setIssueCount(issues.size());

        sprintStats.setTotalStoryPoints(getTotalSpCompleted(issues));
        sprintStats.setAverageStoryPoints(getAverageSpPerIssue(issues));

        calculatePercentages(sprintStats, issues);
        calculateUserStats(sprintStats, issues);

        calculateStoryPointsByDay(sprintStats, issues);
        calculateBurnDown(sprintStats);
    }

    private static int getTotalSpCompleted(List<Issue> issues) {
        return issues.stream()
                .filter(issue -> issue.getStoryPoints() != null)
                .filter(issue -> issue.getState().getType() == IssueStateType.DONE
                                 || issue.getState().getType() == IssueStateType.DONE_SPRINT)
                .mapToInt(Issue::getStoryPoints)
                .sum();
    }

    private static double getAverageSpPerIssue(List<Issue> issues) {
        if (issues.isEmpty()) {
            return 0;
        }
        return (double) issues.stream()
                .filter(issue -> issue.getStoryPoints() != null)
                .mapToInt(Issue::getStoryPoints)
                .sum() / issues.size();
    }

    private void calculateDateRelatedStats(SprintStats sprintStats) {
        Sprint sprint = sprintStats.getSprint();
        sprintStats.setDaysElapsed(calculateDaysElapsed(sprint));
        sprintStats.setDaysLeft(calculateDaysLeft(sprint));
        sprintStats.setPercentageTimeElapsed(calculatePercentageTimeElapsed(sprint));
    }

    private void calculatePercentages(SprintStats sprintStats, List<Issue> issues) {
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

    private void calculateStoryPointsByDay(SprintStats sprintStats, List<Issue> issues) {
        OffsetDateTime startDate = sprintStats.getSprint().getStartDate();
        OffsetDateTime endDate = sprintStats.getSprint().getEndDate();

        if (startDate == null || endDate == null) {
            sprintStats.setStoryPointsByDay(Collections.emptyList());
            return;
        }

        int totalDays = (int) Duration.between(startDate, endDate).toDays();
        List<Integer> storyPointsByDay = new ArrayList<>(Collections.nCopies(totalDays, 0));

        issues.stream()
                .filter(issue -> issue.getStoryPoints() != null)
                .filter(issue -> issue.getState().getType() == IssueStateType.DONE
                                 || issue.getState().getType() == IssueStateType.DONE_SPRINT)
                .forEach(issue -> setSpDoneForIssue(issue, startDate, storyPointsByDay));

        sprintStats.setStoryPointsByDay(storyPointsByDay);
    }

    private void calculateBurnDown(SprintStats sprintStats) {
        List<Integer> storyPointsByDay = sprintStats.getStoryPointsByDay();
        Integer storyPointsPlanned = sprintStats.getSprint().getStoryPointsPlanned();
        if (storyPointsPlanned != null) {
            sprintStats.setBurnDown(constructBurnDown(storyPointsPlanned, storyPointsByDay));
        }
    }

    private static List<Integer> constructBurnDown(
            int storyPointsPlanned,
            List<Integer> storyPointsByDay
    ) {
        return IntStream.range(0, storyPointsByDay.size())
                .map(day -> storyPointsPlanned -
                            storyPointsByDay.stream().limit(day + 1L).mapToInt(Integer::intValue).sum())
                .boxed()
                .toList();
    }

    private void setSpDoneForIssue(Issue issue, OffsetDateTime startDate, List<Integer> storyPointsByDay) {
        var totalDays = storyPointsByDay.size();

        getDoneDateOfIssue(issue).ifPresent(date -> {
            int day = (int) Duration.between(startDate, date).toDays();

            // ensure bounds
            day = Math.clamp(day, 0, totalDays - 1);

            storyPointsByDay.set(day, storyPointsByDay.get(day) + issue.getStoryPoints());
        });
    }

    private Optional<OffsetDateTime> getDoneDateOfIssue(Issue issue) {
        var events = eventService.getEventsForIssue(issue);
        return events.stream()
                .filter(event -> event.getEventType()
                        .getIdentifier()
                        .equals(ImsEventTypes.ISSUE_COMPLETED.getIdentifier()))
                .map(Event::getTimestamp)
                .min(Comparator.naturalOrder());
    }

    private void calculateUserStats(SprintStats sprintStats, List<Issue> issues) {
        Map<UUID, Double> storyPointsByUser = new HashMap<>();

        for (Issue issue : issues) {
            if (issue.getStoryPoints() == null
                || (issue.getState().getType() != IssueStateType.DONE
                    && issue.getState().getType() != IssueStateType.DONE_SPRINT)) {
                continue;
            }

            List<UUID> assignees = issue.getAssigneeIds();
            double storyPointsToAdd = (double) issue.getStoryPoints() / (double) assignees.size();
            assignees.forEach(assignee -> storyPointsByUser.merge(assignee, storyPointsToAdd, Double::sum));
        }

        sprintStats.setUserStats(storyPointsByUser.entrySet().stream()
                .map(entry -> new SprintUserStats(entry.getKey(), entry.getValue()))
                .toList());
    }
}
