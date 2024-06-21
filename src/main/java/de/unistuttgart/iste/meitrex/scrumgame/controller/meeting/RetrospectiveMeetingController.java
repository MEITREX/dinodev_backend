package de.unistuttgart.iste.meitrex.scrumgame.controller.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.meeting.RetrospectiveMeetingActivityService;
import de.unistuttgart.iste.meitrex.scrumgame.service.meeting.RetrospectiveMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class RetrospectiveMeetingController {

    private final RetrospectiveMeetingService         retrospectiveMeetingService;
    private final RetrospectiveMeetingActivityService retrospectiveMeetingActivityService;

    @SchemaMapping
    public RetrospectiveMeeting activeRetrospectiveMeeting(Project project) {
        return retrospectiveMeetingService.findActiveRetrospectiveMeeting(project.getId()).orElse(null);
    }

    @SchemaMapping
    public RetrospectiveMeetingMutation mutateRetrospectiveMeeting(
            ProjectMutation projectMutation
    ) {
        return new RetrospectiveMeetingMutation(projectMutation.getProject());
    }

    @SchemaMapping
    public RetrospectiveMeeting updatePage(
            RetrospectiveMeetingMutation retrospectiveMeetingMutation,
            @Argument RetrospectiveMeetingPage page
    ) {
        return retrospectiveMeetingService.updatePage(retrospectiveMeetingMutation.getProject().getId(), page);
    }

    @SchemaMapping
    public RetrospectiveMeeting awardMedals(RetrospectiveMeetingMutation retrospectiveMeetingMutation) {
        return retrospectiveMeetingService.awardMedals(retrospectiveMeetingMutation.getProject().getId());
    }

    @SchemaMapping
    public RetrospectiveMeeting addComment(
            RetrospectiveMeetingMutation retrospectiveMeetingMutation,
            @Argument UUID columnId,
            @Argument String content
    ) {
        return retrospectiveMeetingActivityService.addComment(retrospectiveMeetingMutation.getProject().getId(),
                columnId,
                content);
    }

    @SchemaMapping
    public RetrospectiveMeeting editComment(
            RetrospectiveMeetingMutation retrospectiveMeetingMutation,
            @Argument UUID commentId,
            @Argument String content
    ) {
        return retrospectiveMeetingActivityService.editComment(retrospectiveMeetingMutation.getProject().getId(),
                commentId,
                content);
    }

    @SchemaMapping
    public RetrospectiveMeeting thumbsUpComment(
            RetrospectiveMeetingMutation retrospectiveMeetingMutation,
            @Argument UUID commentId
    ) {
        return retrospectiveMeetingActivityService.thumbsUpComment(retrospectiveMeetingMutation.getProject().getId(),
                commentId);
    }

    @SchemaMapping
    public RetrospectiveMeeting deleteComment(
            RetrospectiveMeetingMutation retrospectiveMeetingMutation,
            @Argument UUID commentId
    ) {
        return retrospectiveMeetingActivityService.deleteComment(retrospectiveMeetingMutation.getProject().getId(),
                commentId);
    }

    @SchemaMapping
    public RetrospectiveMeeting createRetrospectiveMeeting(
            ProjectMutation projectMutation,
            @Argument RetrospectiveMeetingInput input
    ) {
        return retrospectiveMeetingService.createRetrospectiveMeeting(projectMutation.getProject(), input);
    }

    @SchemaMapping
    public RetrospectiveMeeting finishMeeting(RetrospectiveMeetingMutation retrospectiveMeetingMutation) {
        return retrospectiveMeetingService.finishMeeting(retrospectiveMeetingMutation.getProject().getId());
    }

    @SubscriptionMapping
    public Flux<RetrospectiveMeeting> retrospectiveMeeting(@Argument UUID projectId) {
        return retrospectiveMeetingService.getRetrospectiveMeetingStream(projectId);
    }
}
