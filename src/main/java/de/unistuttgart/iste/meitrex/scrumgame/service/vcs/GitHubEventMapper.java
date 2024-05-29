package de.unistuttgart.iste.meitrex.scrumgame.service.vcs;

import com.fasterxml.jackson.databind.JsonNode;
import de.unistuttgart.iste.meitrex.generated.dto.AllowedDataType;
import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.EventVisibility;
import de.unistuttgart.iste.meitrex.generated.dto.TemplateFieldInput;
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

    @Override
    public Optional<CreateEventInput> mapToCreateEventInput(JsonNode jsonNode, Map<String, String> headers) {
        Optional<String> eventType = getEventType(headers);

        if (eventType.isEmpty()) {
            log.warn("No event type found in headers");
            return Optional.empty();
        }

        return switch (eventType.get()) {
            case "push" -> mapPushEvent(jsonNode);
            case "pull_request" -> mapPullRequestEvent(jsonNode);
            case "pull_request_review" -> mapReviewEvent(jsonNode);
            default -> {
                log.warn("Unsupported event type {}", eventType.get());
                yield Optional.empty();
            }
        };
    }

    private Optional<CreateEventInput> mapPushEvent(JsonNode jsonNode) {
        CreateEventInput baseEvent = createBaseEvent(jsonNode);

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

            baseEvent.getEventData().add(TemplateFieldInput.builder()
                    .setKey("branch")
                    .setType(AllowedDataType.STRING)
                    .setValue(branch)
                    .build());

        }

        JsonNode commitsNode = jsonNode.get("commits");
        if (commitsNode != null) {
            baseEvent.getEventData().add(TemplateFieldInput.builder()
                    .setKey("commitCount")
                    .setType(AllowedDataType.INTEGER)
                    .setValue(Integer.toString(commitsNode.size()))
                    .build());
        }

        JsonNode compareUrlNode = jsonNode.get("compare");
        if (compareUrlNode != null) {
            baseEvent.getEventData().add(TemplateFieldInput.builder()
                    .setKey("branchUrl")
                    .setType(AllowedDataType.STRING)
                    .setValue(compareUrlNode.asText())
                    .build());
        }

        return Optional.of(baseEvent);
    }

    private Optional<CreateEventInput> mapPullRequestEvent(JsonNode jsonNode) {
        return Optional.empty();
    }

    private Optional<CreateEventInput> mapReviewEvent(JsonNode jsonNode) {
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
            return mapReviewEvent(jsonNode, VcsEventTypes.REVIEW_ACCEPT.getIdentifier());
        } else if ("changes_requested".equals(reviewState)) {
            return mapReviewEvent(jsonNode, VcsEventTypes.REVIEW_CHANGE_REQUEST.getIdentifier());
        }

        return Optional.empty();
    }

    private Optional<CreateEventInput> mapReviewEvent(JsonNode jsonNode, @NotNull String eventTypeIdentifier) {
        JsonNode reviewNode = jsonNode.get("review");
        if (reviewNode == null) {
            return Optional.empty();
        }
        CreateEventInput baseEvent = createBaseEvent(jsonNode);

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
        JsonNode titleNode = pullRequestNode.get("title");
        if (titleNode != null) {
            baseEvent.getEventData().add(TemplateFieldInput.builder()
                    .setKey("pullRequestTitle")
                    .setType(AllowedDataType.STRING)
                    .setValue(titleNode.asText())
                    .build());
        }
        JsonNode urlNode = pullRequestNode.get("html_url");
        if (urlNode != null) {
            baseEvent.getEventData().add(TemplateFieldInput.builder()
                    .setKey("pullRequestUrl")
                    .setType(AllowedDataType.STRING)
                    .setValue(urlNode.asText())
                    .build());
        }
    }

    private CreateEventInput createBaseEvent(JsonNode jsonNode) {
        OffsetDateTime timestamp = OffsetDateTime.now();
        List<TemplateFieldInput> baseData = createBaseData(jsonNode);

        JsonNode userNameNode = jsonNode.get("sender").get("login");

        return CreateEventInput.builder()
                .setTimestamp(timestamp)
                .setEventData(baseData)
                .setUserId(githubUsernameToUserId.apply(userNameNode.asText()).orElse(null))
                .setProjectId(null) // currently VCS events are not associated with a single project
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
        JsonNode usernameNode = senderNode.get("login");
        if (usernameNode != null) {
            baseData.add(TemplateFieldInput.builder()
                    .setKey("vcsUsername")
                    .setType(AllowedDataType.STRING)
                    .setValue(usernameNode.asText())
                    .build());
        }

        JsonNode avatarUrlNode = senderNode.get("avatar_url");
        if (avatarUrlNode != null) {
            baseData.add(TemplateFieldInput.builder()
                    .setKey("vcsAvatarUrl")
                    .setType(AllowedDataType.STRING)
                    .setValue(avatarUrlNode.asText())
                    .build());
        }

        JsonNode profileUrlNode = senderNode.get("html_url");
        if (profileUrlNode != null) {
            baseData.add(TemplateFieldInput.builder()
                    .setKey("vcsProfileUrl")
                    .setType(AllowedDataType.STRING)
                    .setValue(profileUrlNode.asText())
                    .build());
        }
    }

    private static void addRepositoryBaseData(JsonNode repositoryNode, List<TemplateFieldInput> baseData) {
        if (repositoryNode == null) {
            return;
        }
        JsonNode nameNode = repositoryNode.get("name");
        if (nameNode != null) {
            baseData.add(TemplateFieldInput.builder()
                    .setKey("repositoryName")
                    .setType(AllowedDataType.STRING)
                    .setValue(nameNode.asText())
                    .build());
        }

        JsonNode repositoryUrlNode = repositoryNode.get("html_url");
        if (repositoryUrlNode != null) {
            baseData.add(TemplateFieldInput.builder()
                    .setKey("repositoryUrl")
                    .setType(AllowedDataType.STRING)
                    .setValue(repositoryUrlNode.asText())
                    .build());
        }
    }

    private Optional<String> getEventType(Map<String, String> headers) {
        return Optional.ofNullable(headers.get("x-github-event"));
    }
}
