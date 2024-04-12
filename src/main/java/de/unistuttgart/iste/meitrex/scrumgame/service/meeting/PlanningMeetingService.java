package de.unistuttgart.iste.meitrex.scrumgame.service.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanningMeetingService {
    public PlanningMeeting createPlanningMeeting(UUID projectId, PlanningMeetingInput input) {
        return PlanningMeeting.builder().setId(UUID.randomUUID()).build();
    }

    public PlanningMeeting updatePlanningMeeting(UUID projectId, UUID id, PlanningMeetingInput input) {
        return createPlanningMeeting(projectId, input);
    }

    public PlanningMeetingMutation mutatePlanningMeeting(UUID projectId, UUID id) {
        return PlanningMeetingMutation.builder().setId(UUID.randomUUID()).build();
    }

    public PlanningMeeting changePage(UUID meetingId, PlanningMeetingPage page) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }

    public PlanningMeeting nextPage(UUID meetingId) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }

    public PlanningMeeting voteAnimal(UUID meetingId, String animal, UUID userId) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }

    public PlanningMeeting voteName(UUID meetingId, String name, UUID userId) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }

    public PlanningMeeting voteStoryPoints(UUID meetingId, StoryPoints storyPoints, UUID userId) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }

    public PlanningMeeting skipIssue(UUID meetingId) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }


    public PlanningMeeting startCountdown(UUID meetingId) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }

    public PlanningMeeting setFinalResult(UUID meetingId, StoryPoints storyPoints) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }

    public Sprint finishMeeting(UUID meetingId) {
        return Sprint.builder().build();
    }

    public PlanningMeeting addSprintIssue(UUID meetingId, UUID issueId) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }

    public PlanningMeeting removeSprintIssue(UUID meetingId, UUID issueId) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }

    public PlanningMeeting setStoryPointLimit(UUID meetingId, int limit) {
        return createPlanningMeeting(UUID.randomUUID(), PlanningMeetingInput.builder().build());
    }


}
