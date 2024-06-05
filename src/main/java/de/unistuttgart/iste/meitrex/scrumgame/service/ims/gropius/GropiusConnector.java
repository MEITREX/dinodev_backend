package de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius;

import de.unistuttgart.iste.gropius.generated.dto.*;
import de.unistuttgart.iste.meitrex.common.graphqlclient.GraphQlRequestExecutor;
import de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsConnector;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.logging.*;
import java.util.stream.*;

import static de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils.distinctByKey;
import static de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius.GropiusMapping.gropiusIssueToScrumGameIssue;

@Slf4j
@RequiredArgsConstructor
public class GropiusConnector implements ImsConnector {

    private final GraphQlRequestExecutor           graphQlRequestExecutor;
    private final GropiusIssueMappingConfiguration mappingConfiguration;

    @Override
    public List<Issue> getIssues(UUID scrumGameProjectId) {
        return graphQlRequestExecutor
                .request(GropiusRequests.getProjectRequest(mappingConfiguration.getImsProjectId()))
                .projectTo(GropiusProjectConnection.class, GropiusProjections.getProjectConnectionProjection())
                .retrieve()
                .map(this::getAllIssuesFromProjectConnection)
                .defaultIfEmpty(List.of())
                .block();
    }

    private List<Issue> getAllIssuesFromProjectConnection(GropiusProjectConnection response) {
        Stream<GropiusIssue> issuesFromProject = response.getNodes().stream()
                .flatMap(project -> project.getIssues().getNodes().stream());
        Stream<GropiusIssue> issuesFromComponents = response.getNodes().stream()
                .flatMap(project -> project.getComponents().getNodes().stream())
                .flatMap(component -> component.getComponent().getIssues().getNodes().stream());

        return Stream.concat(issuesFromProject, issuesFromComponents)
                .filter(distinctByKey(GropiusIssue::getId)) // remove duplicates
                .map(gropiusIssue -> gropiusIssueToScrumGameIssue(gropiusIssue, mappingConfiguration))
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public Optional<Issue> findIssue(String id) {
        return graphQlRequestExecutor
                .request(GropiusRequests.getIssueQueryRequest(id))
                .projectTo(GropiusIssue.class, GropiusProjections.getDefaultIssueProjection())
                .retrieveList()
                .map(issues -> GropiusMapping.gropiusIssueToScrumGameIssue(issues.getFirst(), mappingConfiguration))
                .blockOptional();
    }

    @Override
    public List<Issue> findIssuesBatched(List<String> ids) {
        var request = GropiusRequests.getIssuesQueryRequest(ids);

        List<Issue> issues = graphQlRequestExecutor
                .request(request)
                .projectTo(GropiusIssue.class, GropiusProjections.getDefaultIssueProjection())
                .retrieveList()
                .map(gropiusIssues -> gropiusIssues.stream()
                        .map(gropiusIssue -> GropiusMapping.gropiusIssueToScrumGameIssue(gropiusIssue,
                                mappingConfiguration))
                        .toList())
                .blockOptional()
                .orElseThrow();

        return MeitrexCollectionUtils.sortByKeys(issues, ids, Issue::getId);
    }

    @Override
    public Issue changeIssueTitle(String issueId, String title) {
        return graphQlRequestExecutor
                .request(GropiusRequests.getChangeIssueTitleRequest(issueId, title))
                .projectTo(GropiusChangeIssueTitlePayload.class,
                        GropiusProjections.getChangeIssueTitlePayloadResponseProjection())
                .retrieve()
                .map(response -> gropiusIssueToScrumGameIssue(response.getTitleChangedEvent().getIssue(),
                        mappingConfiguration))
                .block();
    }

    @Override
    public Issue changeIssueDescription(String issueId, String description) {
        var request = new UpdateBodyMutationRequest();
        request.setInput(new GropiusUpdateBodyInput(description, issueId));

        var projection = new UpdateBodyPayloadResponseProjection()
                .body(new BodyResponseProjection()
                        .issue(GropiusProjections.getDefaultIssueProjection()));

        return graphQlRequestExecutor
                .request(request)
                .projectTo(GropiusUpdateBodyPayload.class, projection)
                .retrieve()
                .map(response -> gropiusIssueToScrumGameIssue(response.getBody().getIssue(), mappingConfiguration))
                .block();
    }

    @Override
    public Issue changeIssueState(String issueId, IssueState issueState) {
        var request = new ChangeIssueStateMutationRequest();
        request.setInput(new GropiusChangeIssueStateInput(issueId, issueState.getImsStateId()));

        var projection = new ChangeIssueStatePayloadResponseProjection()
                .stateChangedEvent(new StateChangedEventResponseProjection()
                        .issue(GropiusProjections.getDefaultIssueProjection()));

        return graphQlRequestExecutor
                .request(request)
                .projectTo(GropiusChangeIssueStatePayload.class, projection)
                .retrieve()
                .map(response -> gropiusIssueToScrumGameIssue(response.getStateChangedEvent().getIssue(),
                        mappingConfiguration))
                .block();
    }

    @Override
    public Issue changeIssuePriority(String issueId, IssuePriority priority) {
        var request = new ChangeIssuePriorityMutationRequest();
        request.setInput(new GropiusChangeIssuePriorityInput(issueId,
                mappingConfiguration.getIssuePriorityMapping().getIssuePriorityId(priority)));

        var projection = new ChangeIssuePriorityPayloadResponseProjection()
                .priorityChangedEvent(new PriorityChangedEventResponseProjection()
                        .issue(GropiusProjections.getDefaultIssueProjection()));

        return graphQlRequestExecutor
                .request(request)
                .projectTo(GropiusChangeIssuePriorityPayload.class, projection)
                .retrieve()
                .map(response -> gropiusIssueToScrumGameIssue(response.getPriorityChangedEvent().getIssue(),
                        mappingConfiguration))
                .block();
    }

    @Override
    public Issue changeIssueType(String issueId, String typeName) {
        var request = new ChangeIssueTypeMutationRequest();
        request.setInput(new GropiusChangeIssueTypeInput(issueId, typeName));

        var projection = new ChangeIssueTypePayloadResponseProjection()
                .typeChangedEvent(new TypeChangedEventResponseProjection()
                        .issue(GropiusProjections.getDefaultIssueProjection()));

        return graphQlRequestExecutor
                .request(request)
                .projectTo(GropiusChangeIssueTypePayload.class, projection)
                .retrieve()
                .map(response -> gropiusIssueToScrumGameIssue(response.getTypeChangedEvent().getIssue(),
                        mappingConfiguration))
                .block();
    }

    @Override
    public Issue changeSprintOfIssue(String issueId, @Nullable Integer sprintNumber) {

        return changeTemplateField(issueId, mappingConfiguration.getSprintFieldName(), sprintNumber);
    }

    @Override
    public Issue changeEstimationOfIssue(String issueId, TShirtSizeEstimation estimation) {

        return changeTemplateField(issueId,
                mappingConfiguration.getEstimationTemplateFieldName(),
                estimation.toString());
    }

    private Issue changeTemplateField(
            String issueId,
            String fieldName,
            Object value) {

        var request = new ChangeIssueTemplatedFieldMutationRequest();
        request.setInput(GropiusChangeIssueTemplatedFieldInput.builder()
                .setIssue(issueId)
                .setName(fieldName)
                .setValue(value)
                .build());

        var projection = new ChangeIssueTemplatedFieldPayloadResponseProjection()
                .templatedFieldChangedEvent(new TemplatedFieldChangedEventResponseProjection()
                        .issue(GropiusProjections.getDefaultIssueProjection()));

        return graphQlRequestExecutor
                .request(request)
                .projectTo(GropiusChangeIssueTemplatedFieldPayload.class, projection)
                .retrieve()
                .map(response -> {
                    if (response.getTemplatedFieldChangedEvent() == null) {
                        return findIssue(issueId).orElseThrow();
                    }
                    return gropiusIssueToScrumGameIssue(response.getTemplatedFieldChangedEvent().getIssue(),
                            mappingConfiguration);
                })
                .block();
    }

    @Override
    public Issue assignIssue(String issueId, UUID assigneeId) {
        var request = new CreateAssignmentMutationRequest();
        request.setInput(GropiusCreateAssignmentInput.builder()
                .setIssue(issueId)
                .setUser(assigneeId.toString())
                .build());

        var projection = new CreateAssignmentPayloadResponseProjection()
                .assignment(new AssignmentResponseProjection()
                        .issue(GropiusProjections.getDefaultIssueProjection()));

        return graphQlRequestExecutor
                .request(request)
                .projectTo(GropiusCreateAssignmentPayload.class, projection)
                .retrieve()
                .map(response -> gropiusIssueToScrumGameIssue(response.getAssignment().getIssue(),
                        mappingConfiguration))
                .block();
    }

    @Override
    public Issue addCommentToIssue(String issueId, String comment, @Nullable String optionalParentIssueId) {
        var request = new CreateIssueCommentMutationRequest();
        request.setInput(GropiusCreateIssueCommentInput.builder()
                .setIssue(issueId)
                .setBody(comment)
                .setAnswers(optionalParentIssueId)
                .build());

        var projection = new CreateIssueCommentPayloadResponseProjection()
                .issueComment(new IssueCommentResponseProjection()
                        .issue(GropiusProjections.getDefaultIssueProjection()));

        try {
            return graphQlRequestExecutor
                    .request(request)
                    .projectTo(GropiusCreateIssueCommentPayload.class, projection)
                    .retrieve()
                    .map(response -> gropiusIssueToScrumGameIssue(response.getIssueComment().getIssue(),
                            mappingConfiguration))
                    .block();
        } catch (Exception e) {
            // try without parent id
            request.setInput(GropiusCreateIssueCommentInput.builder()
                    .setIssue(issueId)
                    .setBody(comment)
                    .build());

            return graphQlRequestExecutor
                    .request(request)
                    .projectTo(GropiusCreateIssueCommentPayload.class, projection)
                    .retrieve()
                    .map(response -> gropiusIssueToScrumGameIssue(response.getIssueComment().getIssue(),
                            mappingConfiguration))
                    .log("Commented on issue", Level.INFO)
                    .block();
        }
    }

    @Override
    public Issue createIssue(CreateIssueInput createIssueInput) {
        var request = new CreateIssueMutationRequest();
        request.setInput(GropiusCreateIssueInput.builder()
                .setTrackables(List.of(mappingConfiguration.getImsProjectId()))
                .setTitle(createIssueInput.getTitle())
                .setBody(createIssueInput.getDescription())
                .setState(mappingConfiguration.getIssueStateConverter()
                        .getIssueStateId(createIssueInput.getStateName()))
                .setType(mappingConfiguration.getIssueTypeMapping().getIssueTypeId(createIssueInput.getTypeName()))
                .setTemplate(mappingConfiguration.getIssueTemplateId())
                .setType(mappingConfiguration.getIssueTypeMapping().getIssueTypeId(createIssueInput.getTypeName()))
                .setTemplatedFields(List.of(
                        GropiusJSONFieldInput.builder()
                                .setName(mappingConfiguration.getSprintFieldName())
                                .setValue(createIssueInput.getSprintNumber())
                                .build()))
                .build());

        var projection = new CreateIssuePayloadResponseProjection()
                .issue(GropiusProjections.getDefaultIssueProjection());

        return graphQlRequestExecutor
                .request(request)
                .projectTo(GropiusCreateIssuePayload.class, projection)
                .retrieve()
                .map(response -> gropiusIssueToScrumGameIssue(response.getIssue(), mappingConfiguration))
                .block();
    }

    @Override
    public List<CreateEventInput> getEventsForIssue(String issueId,
            OffsetDateTime since) {
        var projection = new IssueResponseProjection()
                .id()
                .title()
                .assignments(new AssignmentConnectionResponseProjection()
                        .nodes(new AssignmentResponseProjection()
                                .id()
                                .user(new UserResponseProjection().id().username())))
                .timelineItems(new IssueTimelineItemsParametrizedInput()
                                .filter(GropiusTimelineItemFilterInput.builder()
                                        .setLastModifiedAt(GropiusDateTimeFilterInput.builder()
                                                .setGt(since.toString())
                                                .build())
                                        .build())
                                .orderBy(GropiusTimelineItemOrder.builder()
                                        .setField(GropiusTimelineItemOrderField.CREATED_AT)
                                        .setDirection(GropiusOrderDirection.DESC)
                                        .build()),
                        GropiusProjections.TIMELINE_ITEM_CONNECTION_RESPONSE_PROJECTION);

        return graphQlRequestExecutor
                .request(GropiusRequests.getIssueQueryRequest(issueId))
                .projectTo(GropiusIssue.class, projection)
                .retrieveList()
                .map(issues -> getTimeLineItemsOfFirstIssue(mappingConfiguration, issues))
                .defaultIfEmpty(List.of())
                .block();
    }

    private static List<CreateEventInput> getTimeLineItemsOfFirstIssue(GropiusIssueMappingConfiguration mappingConfiguration,
            List<GropiusIssue> issues) {
        if (issues.isEmpty()) {
            return List.of();
        }

        var issue = issues.getFirst();
        return issue.getTimelineItems().getNodes().stream()
                .flatMap(timelineItem -> GropiusTimelineItemToEventConverter
                        .convertTimelineItemToEvents(issue, timelineItem, mappingConfiguration).stream())
                .toList();
    }
}
