package de.unistuttgart.iste.meitrex.scrumgame.service.sprint;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.EventService;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsServiceExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SprintStatsServiceTest {

    @Mock
    private ImsServiceExtension imsServiceExtension;

    @Mock
    private EventService eventService;

    @Mock
    private SprintService sprintService;

    @InjectMocks
    private SprintStatsService sprintStatsService;

    @Test
    void testCalculateDateRelatedStats() {
        // Arrange
        Sprint sprint = Sprint.builder()
                .setStartDate(OffsetDateTime.now().minusDays(10))
                .setEndDate(OffsetDateTime.now().plusDays(10))
                .setProject(Project.builder().setId(UUID.randomUUID()).build())
                .build();

        when(imsServiceExtension.getIssuesBySprints(any())).thenReturn(Map.of(sprint, List.of()));

        // Act
        SprintStats sprintStats = sprintStatsService.getSprintStats(sprint);

        // Assert
        assertThat(sprintStats.getDaysElapsed(), is(10));
        assertThat(sprintStats.getDaysLeft(), is(9)); // it's 10 - 1 because of rounding down
        assertThat(sprintStats.getPercentageTimeElapsed(), closeTo(50, 0.1));
    }

    @Test
    void testCalculateDateRelatedStatsNullDates() {
        // Arrange
        Sprint sprint = Sprint.builder()
                .setStartDate(null)
                .setEndDate(null)
                .setProject(Project.builder().setId(UUID.randomUUID()).build())
                .build();

        when(imsServiceExtension.getIssuesBySprints(any())).thenReturn(Map.of(sprint, List.of()));

        // Act
        SprintStats sprintStats = sprintStatsService.getSprintStats(sprint);

        // Assert
        assertThat(sprintStats.getDaysElapsed(), is(0));
        assertThat(sprintStats.getDaysLeft(), is(0));
        assertThat(sprintStats.getPercentageTimeElapsed(), closeTo(100, 0.1)); // assume sprint is over
    }

    @Test
    void testCaching() {
        // Arrange
        Sprint sprint = Sprint.builder()
                .setProject(Project.builder().setId(UUID.randomUUID()).build())
                .build();

        when(imsServiceExtension.getIssuesBySprints(any())).thenReturn(Map.of(sprint, List.of()));

        // Act
        SprintStats sprintStats1 = sprintStatsService.getSprintStats(sprint);
        SprintStats sprintStats2 = sprintStatsService.getSprintStats(sprint);

        // Assert
        assertThat(sprintStats1, is(sameInstance(sprintStats2)));
    }

    @Test
    void testBurndown() {
        // Arrange
        Sprint sprint = Sprint.builder()
                .setProject(Project.builder().setId(UUID.randomUUID()).build())
                .setStoryPointsPlanned(10)
                .setStartDate(OffsetDateTime.now().minusDays(10))
                .setEndDate(OffsetDateTime.now())
                .build();

        Issue issue1 = createIssue(3, IssueStateType.DONE);
        Issue issue2 = createIssue(5, IssueStateType.DONE_SPRINT);
        Issue issue3 = createIssue(2, IssueStateType.DONE);

        Event issue1Completed = createIssueCompletedEvent(issue1, OffsetDateTime.now().minusDays(5));
        Event issue2Completed = createIssueCompletedEvent(issue2, OffsetDateTime.now().minusDays(3));
        Event issue3Completed = createIssueCompletedEvent(issue3, OffsetDateTime.now().minusDays(1));

        when(imsServiceExtension.getIssuesBySprints(any()))
                .thenReturn(Map.of(sprint, List.of(issue1, issue2, issue3)));

        when(eventService.getEventsForIssue(issue1)).thenReturn(List.of(issue1Completed));
        when(eventService.getEventsForIssue(issue2)).thenReturn(List.of(issue2Completed));
        when(eventService.getEventsForIssue(issue3)).thenReturn(List.of(issue3Completed));

        when(sprintService.findSprint(any(), any())).thenReturn(Optional.empty());

        // Act
        SprintStats sprintStats = sprintStatsService.getSprintStats(sprint);

        // Assert
        assertThat(sprintStats.getBurnDown(), contains(
                10,
                10,
                10,
                10,
                10,
                7,
                7,
                2,
                2,
                0));
    }

    @Test
    void testBasicIssueStats() {
        // Arrange
        Sprint sprint = Sprint.builder()
                .setProject(Project.builder().setId(UUID.randomUUID()).build())
                .setStoryPointsPlanned(10)
                .setStartDate(OffsetDateTime.now().minusDays(10))
                .setEndDate(OffsetDateTime.now())
                .build();

        Issue issue1 = createIssue(3, IssueStateType.DONE);
        Issue issue2 = createIssue(5, IssueStateType.DONE_SPRINT);
        Issue issue3 = createIssue(2, IssueStateType.DONE);

        when(imsServiceExtension.getIssuesBySprints(any()))
                .thenReturn(Map.of(sprint, List.of(issue1, issue2, issue3)));
        when(sprintService.findSprint(any(), any())).thenReturn(Optional.empty());

        // Act
        SprintStats sprintStats = sprintStatsService.getSprintStats(sprint);

        // Assert
        assertThat(sprintStats.getAverageStoryPoints(), closeTo(3.3, 0.1));
        assertThat(sprintStats.getTotalStoryPoints(), is(10));
    }


    private Issue createIssue(int sp, IssueStateType stateType) {
        return Issue.builder()
                .setStoryPoints(sp)
                .setId(UUID.randomUUID().toString())
                .setState(IssueState.builder().setType(stateType).build())
                .setAssigneeIds(List.of(UUID.randomUUID()))
                .build();
    }

    private Event createIssueCompletedEvent(Issue issue, OffsetDateTime timestamp) {
        return DefaultEvent.builder()
                .setTimestamp(timestamp)
                .setEventType((DefaultEventType) ImsEventTypes.ISSUE_COMPLETED)
                .setEventData(List.of(
                        DefaultTemplateField.builder()
                                .setKey("issueId")
                                .setValue(issue.getId())
                                .setType(AllowedDataType.STRING)
                                .build()
                ))
                .build();
    }
}