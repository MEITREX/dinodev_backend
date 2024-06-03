package de.unistuttgart.iste.meitrex.scrumgame.service.vcs;

import com.fasterxml.jackson.databind.JsonNode;
import de.unistuttgart.iste.meitrex.generated.dto.AllowedDataType;
import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.EventVisibility;
import de.unistuttgart.iste.meitrex.generated.dto.TemplateFieldInput;
import de.unistuttgart.iste.meitrex.scrumgame.service.project.ProjectService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.function.*;

@Slf4j
@RequiredArgsConstructor
public class GitHubEventMapper implements VscEventMapper {

    private final Function<String, Optional<UUID>> githubUsernameToUserId;
    private final ProjectService projectService;

    @Override
    public List<CreateEventInput> mapToCreateEventInputList(JsonNode jsonNode, Map<String, String> headers) {
        return projectService.getAllProjects() // todo: only projects with the respective repository
                .stream()
                .flatMap(project -> mapToSingleEvent(jsonNode, headers, project.getId()).stream())
                .toList();
    }

    public Optional<CreateEventInput> mapToSingleEvent(JsonNode jsonNode, Map<String, String> headers, UUID projectId) {
        Optional<String> eventType = getEventType(headers);

        if (eventType.isEmpty()) {
            log.warn("No event type found in headers");
            return Optional.empty();
        }

        return switch (eventType.get()) {
            case "push" -> mapPushEvent(jsonNode, projectId);
            case "pull_request" -> mapPullRequestEvent(jsonNode, projectId);
            case "pull_request_review" -> mapReviewEvent(jsonNode, projectId);
            default -> {
                log.warn("Unsupported event type {}", eventType.get());
                yield Optional.empty();
            }
        };
    }

    private Optional<CreateEventInput> mapPushEvent(JsonNode jsonNode, UUID projectId) {
        CreateEventInput baseEvent = createBaseEvent(jsonNode, projectId);

        baseEvent.setEventTypeIdentifier(VcsEventTypes.PUSH.getIdentifier());

        JsonNode refNode = jsonNode.get("ref");
        if (refNode != null) {
            String branch = refNode.asText();
            if (branch.startsWith("refs/heads/")) {
                branch = branch.substring("refs/heads/".length());
            }
            if ("master".equals(branch) || "main".equals(branch)) {
                baseEvent.setVisibility(EventVisibility.PUBLIC);
            } else {
                baseEvent.setVisibility(EventVisibility.PRIVATE);
            }

            addStringField(baseEvent.getEventData(), "branch", branch);
        }

        List<TemplateFieldInput> fields = baseEvent.getEventData();
        ifIntValuePresent(jsonNode, "commits",
                value -> addIntField(fields, "commitCount", value));
        ifStringValuePresent(jsonNode, "compare",
                value -> addStringField(fields, "branchUrl", value));

        return Optional.of(baseEvent);
    }

    private Optional<CreateEventInput> mapPullRequestEvent(JsonNode jsonNode, UUID projectId) {
        CreateEventInput baseEvent = createBaseEvent(jsonNode, projectId);

        ifStringValuePresent(jsonNode, "action", value -> {
            if ("opened".equals(value)) {
                baseEvent.setEventTypeIdentifier(VcsEventTypes.OPEN_PULL_REQUEST.getIdentifier());
            } else if ("closed".equals(value)) {
                baseEvent.setEventTypeIdentifier(VcsEventTypes.CLOSE_PULL_REQUEST.getIdentifier());
            }
        });

        JsonNode pullRequestNode = jsonNode.get("pull_request");
        if (pullRequestNode == null) {
            return Optional.empty();
        }

        addPullRequestData(jsonNode, baseEvent);

        return Optional.of(baseEvent);
    }

    private Optional<CreateEventInput> mapReviewEvent(JsonNode jsonNode, UUID projectId) {
        String action = jsonNode.get("action").asText();
        if (!"submitted".equals(action)) {
            return Optional.empty();
        }

        JsonNode reviewNode = jsonNode.get("review");
        if (reviewNode == null) {
            return Optional.empty();
        }

        String reviewState = reviewNode.get("state").asText();
        if ("approved".equals(reviewState)) {
            return mapReviewEvent(jsonNode, VcsEventTypes.REVIEW_ACCEPT.getIdentifier(), projectId);
        } else if ("changes_requested".equals(reviewState)) {
            return mapReviewEvent(jsonNode, VcsEventTypes.REVIEW_CHANGE_REQUEST.getIdentifier(), projectId);
        }

        return Optional.empty();
    }

    private Optional<CreateEventInput> mapReviewEvent(
            JsonNode jsonNode,
            @NotNull String eventTypeIdentifier,
            UUID projectId
    ) {
        JsonNode reviewNode = jsonNode.get("review");
        if (reviewNode == null) {
            return Optional.empty();
        }
        CreateEventInput baseEvent = createBaseEvent(jsonNode, projectId);

        baseEvent.setEventTypeIdentifier(eventTypeIdentifier);

        setReviewTimestamp(reviewNode, baseEvent);
        addPullRequestData(jsonNode, baseEvent);

        return Optional.of(baseEvent);
    }

    private static void setReviewTimestamp(JsonNode reviewNode, CreateEventInput baseEvent) {
        OffsetDateTime timestamp = OffsetDateTime.now();
        try {
            timestamp = OffsetDateTime.parse(reviewNode.get("submitted_at").asText());
        } catch (Exception e) {
            log.warn("Failed to parse timestamp", e);
        }
        baseEvent.setTimestamp(timestamp);
    }

    private static void addPullRequestData(JsonNode jsonNode, CreateEventInput baseEvent) {
        JsonNode pullRequestNode = jsonNode.get("pull_request");
        if (pullRequestNode == null) {
            return;
        }

        ifStringValuePresent(pullRequestNode, "title",
                value -> addStringField(baseEvent.getEventData(), "pullRequestTitle", value));
        ifStringValuePresent(pullRequestNode, "html_url",
                value -> addStringField(baseEvent.getEventData(), "pullRequestUrl", value));

        ifIntValuePresent(pullRequestNode, "commits",
                value -> addIntField(baseEvent.getEventData(), "commitCount", value));
        ifIntValuePresent(pullRequestNode, "additions",
                value -> addIntField(baseEvent.getEventData(), "additions", value));
        ifIntValuePresent(pullRequestNode, "deletions",
                value -> addIntField(baseEvent.getEventData(), "deletions", value));
    }

    private static void ifStringValuePresent(JsonNode node, String key, Consumer<String> consumer) {
        JsonNode valueNode = node.get(key);
        if (valueNode != null) {
            consumer.accept(valueNode.asText());
        }
    }

    private static void ifIntValuePresent(JsonNode node, String key, IntConsumer consumer) {
        JsonNode valueNode = node.get(key);
        if (valueNode != null) {
            consumer.accept(valueNode.asInt());
        }
    }

    private static void addStringField(List<TemplateFieldInput> fields, String key, String value) {
        fields.add(TemplateFieldInput.builder()
                .setKey(key)
                .setType(AllowedDataType.STRING)
                .setValue(value)
                .build());
    }

    private static void addIntField(List<TemplateFieldInput> fields, String key, Integer value) {
        fields.add(TemplateFieldInput.builder()
                .setKey(key)
                .setType(AllowedDataType.INTEGER)
                .setValue(Integer.toString(value))
                .build());
    }

    private CreateEventInput createBaseEvent(JsonNode jsonNode, UUID projectId) {
        OffsetDateTime timestamp = OffsetDateTime.now();
        List<TemplateFieldInput> baseData = createBaseData(jsonNode);

        JsonNode userNameNode = jsonNode.get("sender").get("login");

        return CreateEventInput.builder()
                .setTimestamp(timestamp)
                .setEventData(baseData)
                .setUserId(githubUsernameToUserId.apply(userNameNode.asText()).orElse(null))
                .setProjectId(projectId)
                .build();
    }

    private List<TemplateFieldInput> createBaseData(JsonNode jsonNode) {
        List<TemplateFieldInput> baseData = new ArrayList<>();

        JsonNode repositoryNode = jsonNode.get("repository");
        addRepositoryBaseData(repositoryNode, baseData);

        JsonNode senderNode = jsonNode.get("sender");
        addSenderBaseData(senderNode, baseData);

        return baseData;
    }

    private static void addSenderBaseData(JsonNode senderNode, List<TemplateFieldInput> baseData) {
        if (senderNode == null) {
            return;
        }
        ifStringValuePresent(senderNode, "login",
                value -> addStringField(baseData, "vcsUsername", value));
        ifStringValuePresent(senderNode, "avatar_url",
                value -> addStringField(baseData, "vcsAvatarUrl", value));
        ifStringValuePresent(senderNode, "html_url",
                value -> addStringField(baseData, "vcsProfileUrl", value));
    }

    private static void addRepositoryBaseData(JsonNode repositoryNode, List<TemplateFieldInput> baseData) {
        if (repositoryNode == null) {
            return;
        }
        ifStringValuePresent(repositoryNode, "name",
                value -> addStringField(baseData, "repositoryName", value));
        ifStringValuePresent(repositoryNode, "html_url",
                value -> addStringField(baseData, "repositoryUrl", value));
    }

    private Optional<String> getEventType(Map<String, String> headers) {
        return Optional.ofNullable(headers.get("x-github-event"));
    }
}
