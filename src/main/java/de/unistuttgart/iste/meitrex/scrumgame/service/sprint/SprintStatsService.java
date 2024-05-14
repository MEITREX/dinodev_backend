package de.unistuttgart.iste.meitrex.scrumgame.service.sprint;

import de.unistuttgart.iste.meitrex.generated.dto.Issue;
import de.unistuttgart.iste.meitrex.generated.dto.IssueStateType;
import de.unistuttgart.iste.meitrex.generated.dto.Sprint;
import de.unistuttgart.iste.meitrex.generated.dto.SprintStats;
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

    public SprintStats getSprintStats(Sprint sprint) {
        SprintStats sprintStats = new SprintStats();
        sprintStats.setSprint(sprint);

        sprintStats.setDaysElapsed(calculateDaysElapsed(sprint));
        sprintStats.setDaysLeft(calculateDaysLeft(sprint));
        sprintStats.setPercentageTimeElapsed(calculatePercentageTimeElapsed(sprint));

        List<Issue> issues = imsService.getIssuesBySprints(List.of(sprint)).get(sprint);
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

        Map<IssueStateType, Integer> storyPointsByState = issues.stream()
                .filter(issue -> issue.getStoryPoints() != null && issue.getState() != null)
                .collect(Collectors.groupingBy(issue -> issue.getState().getType(),
                        Collectors.summingInt(Issue::getStoryPoints)));

        sprintStats.setPercentageStoryPointsCompleted(getPercentage(storyPointsByState,
                totalStoryPoints,
                IssueStateType.DONE,
                IssueStateType.DONE_SPRINT));
        sprintStats.setPercentageStoryPointsInProgress(getPercentage(storyPointsByState,
                totalStoryPoints,
                IssueStateType.IN_PROGRESS));
        sprintStats.setPercentageStoryPointsNotStarted(getPercentage(storyPointsByState,
                totalStoryPoints,
                IssueStateType.SPRINT_BACKLOG));

        return sprintStats;
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
