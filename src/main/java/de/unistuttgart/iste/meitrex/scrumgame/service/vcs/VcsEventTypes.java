package de.unistuttgart.iste.meitrex.scrumgame.service.vcs;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class VcsEventTypes {

    private static final List<DefaultFieldSchemaDefinition> DEFAULT_FIELDS = List.of(
            DefaultFieldSchemaDefinition.builder()
                    .setName("repositoryName")
                    .setType(AllowedDataType.STRING)
                    .setDescription("The name of the repository.")
                    .setRequired(false)
                    .build(),
            DefaultFieldSchemaDefinition.builder()
                    .setName("repositoryUrl")
                    .setType(AllowedDataType.STRING)
                    .setDescription("The URL of the repository.")
                    .setRequired(false)
                    .build(),
            DefaultFieldSchemaDefinition.builder()
                    .setName("vcsUsername")
                    .setType(AllowedDataType.STRING)
                    .setDescription("The Vcs username.")
                    .setRequired(false)
                    .build(),
            DefaultFieldSchemaDefinition.builder()
                    .setName("vcsAvatarUrl")
                    .setType(AllowedDataType.STRING)
                    .setDescription("The URL of the Vcs avatar.")
                    .setRequired(false)
                    .build(),
            DefaultFieldSchemaDefinition.builder()
                    .setName("vcsProfileUrl")
                    .setType(AllowedDataType.STRING)
                    .setDescription("The URL of the Vcs profile.")
                    .setRequired(false)
                    .build());

    private static List<DefaultFieldSchemaDefinition> defaultFieldsAnd(DefaultFieldSchemaDefinition... additionalFields) {
        List<DefaultFieldSchemaDefinition> fields = new ArrayList<>(DEFAULT_FIELDS);
        fields.addAll(List.of(additionalFields));
        return fields;
    }

    private static final List<DefaultFieldSchemaDefinition> PULL_REQUEST_FIELDS = defaultFieldsAnd(
            DefaultFieldSchemaDefinition.builder()
                    .setName("pullRequestTitle")
                    .setType(AllowedDataType.STRING)
                    .setDescription("The title of the pull request.")
                    .setRequired(false)
                    .build(),
            DefaultFieldSchemaDefinition.builder()
                    .setName("pullRequestUrl")
                    .setType(AllowedDataType.STRING)
                    .setDescription("The URL of the pull request.")
                    .setRequired(false)
                    .build(),
            DefaultFieldSchemaDefinition.builder()
                    .setName("commitCount")
                    .setType(AllowedDataType.INTEGER)
                    .setDescription("The number of commits.")
                    .setRequired(false)
                    .build(),
            DefaultFieldSchemaDefinition.builder()
                    .setName("additions")
                    .setType(AllowedDataType.INTEGER)
                    .setDescription("The number of additions.")
                    .setRequired(false)
                    .build(),
            DefaultFieldSchemaDefinition.builder()
                    .setName("deletions")
                    .setType(AllowedDataType.INTEGER)
                    .setDescription("The number of deletions.")
                    .setRequired(false)
                    .build()
    );

    public static final EventType PUSH = DefaultEventType.builder()
            .setIdentifier("PUSH")
            .setDescription("A push event.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setMessageTemplate("pushed to branch ${branch} with ${commitCount} commits.")
            .setEventSchema(DefaultSchemaDefinition.builder()
                    .setFields(
                            defaultFieldsAnd(
                                    DefaultFieldSchemaDefinition.builder()
                                            .setName("branch")
                                            .setType(AllowedDataType.STRING)
                                            .setDescription("The name of the branch.")
                                            .setRequired(false)
                                            .build(),
                                    DefaultFieldSchemaDefinition.builder()
                                            .setName("branchUrl")
                                            .setType(AllowedDataType.STRING)
                                            .setDescription("The URL of the branch.")
                                            .setRequired(false)
                                            .build(),
                                    DefaultFieldSchemaDefinition.builder()
                                            .setName("commitCount")
                                            .setType(AllowedDataType.INTEGER)
                                            .setDescription("The number of commits.")
                                            .setRequired(false)
                                            .build()))
                    .build())
            .build();

    public static final EventType OPEN_PULL_REQUEST = DefaultEventType.builder()
            .setIdentifier("OPEN_PULL_REQUEST")
            .setDescription("An open pull request event.")
            .setMessageTemplate(
                    "opened the pull request ${pullRequestTitle} with ${commitCount} commits in the repository ${repositoryName}.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setEventSchema(DefaultSchemaDefinition.builder()
                    .setFields(PULL_REQUEST_FIELDS)
                    .build())
            .build();

    public static final EventType CLOSE_PULL_REQUEST = DefaultEventType.builder()
            .setIdentifier("CLOSE_PULL_REQUEST")
            .setDescription("A close pull request event.")
            .setMessageTemplate(
                    "closed the pull request ${pullRequestTitle} with ${commitCount} commits in the repository ${repositoryName}.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setEventSchema(DefaultSchemaDefinition.builder()
                    .setFields(PULL_REQUEST_FIELDS)
                    .build())
            .build();

    public static final EventType REVIEW_ACCEPT = DefaultEventType.builder()
            .setIdentifier("REVIEW_ACCEPT")
            .setDescription("A review submitted event.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setMessageTemplate("accepted the pull request ${pullRequestTitle}.")
            .setEventSchema(DefaultSchemaDefinition.builder()
                    .setFields(PULL_REQUEST_FIELDS)
                    .build())
            .build();

    public static final EventType REVIEW_CHANGE_REQUEST = DefaultEventType.builder()
            .setIdentifier("REVIEW_CHANGE_REQUEST")
            .setDescription("A review change request event.")
            .setMessageTemplate("requested changes for the pull request ${pullRequestTitle}.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setEventSchema(DefaultSchemaDefinition.builder()
                    .setFields(PULL_REQUEST_FIELDS)
                    .build())
            .build();

    public static List<EventType> allEventTypes() {
        return List.of(PUSH, OPEN_PULL_REQUEST, CLOSE_PULL_REQUEST, REVIEW_ACCEPT, REVIEW_CHANGE_REQUEST);
    }

}
