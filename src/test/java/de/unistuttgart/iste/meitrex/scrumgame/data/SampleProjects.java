package de.unistuttgart.iste.meitrex.scrumgame.data;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.*;

import java.util.*;

public class SampleProjects {

    private SampleProjects() {
    }


    public static CreateProjectInput getSampleCreateProjectInput() {
        return CreateProjectInput.builder()
                .setName("Test Project")
                .setDescription("Test Description")
                .setProjectSettings(ProjectSettingsInput.builder()
                        .setCodeRepositorySettings(CodeRepositorySettingsInput.builder()
                                .setRepositories(
                                        List.of(RepositoryDefinitionInput.builder()
                                                        .setName("Test Repository")
                                                        .setUrl("Test URL")
                                                        .setIcon(IconInput.builder()
                                                                .setMdiIcon("Test Icon")
                                                                .build())
                                                        .build(),
                                                RepositoryDefinitionInput.builder()
                                                        .setName("Test Repository 2")
                                                        .setUrl("Test URL 2")
                                                        .setIcon(IconInput.builder()
                                                                .setPath("Test Path")
                                                                .build())
                                                        .build())
                                )
                                .build())
                        .setImsSettings(ImsSettingsInput.builder()
                                .setImsName("Test IMS")
                                .setImsProjectId("Test IMS Project ID")
                                .setImsProjectUrl("Test IMS Project URL")
                                .setImsIcon(IconInput.builder()
                                        .setMdiIcon("Test Icon")
                                        .setPath("Test Path")
                                        .build())
                                .setImsIssueTemplateId("Test IMS Issue Template ID")
                                .setEffortEstimationFieldName("Test Effort Estimation Field Name")
                                .setSprintFieldName("Test Sprint Field Name")
                                .setIssuePriorities(List.of(
                                        IssuePriorityInput.builder()
                                                .setImsPriorityId("Test IMS Priority ID")
                                                .setIssuePriority(IssuePriority.HIGH)
                                                .build(),
                                        IssuePriorityInput.builder()
                                                .setImsPriorityId("Test IMS Priority ID 2")
                                                .setIssuePriority(IssuePriority.LOW)
                                                .build()))
                                .setIssueStates(List.of(
                                        IssueStateInput.builder()
                                                .setName("Test State")
                                                .setImsStateId("Test IMS State ID")
                                                .setType(IssueStateType.SPRINT_BACKLOG)
                                                .build(),
                                        IssueStateInput.builder()
                                                .setName("Test State 2")
                                                .setImsStateId("Test IMS State ID 2")
                                                .setType(IssueStateType.IN_PROGRESS)
                                                .build()))
                                .build())
                        .setDefinitionOfDone(List.of(
                                DefinitionOfDoneItemInput.builder()
                                        .setText("Test DoD Item")
                                        .setRequired(true)
                                        .build(),
                                DefinitionOfDoneItemInput.builder()
                                        .setText("Test DoD Item 2")
                                        .setRequired(false)
                                        .setImplies(List.of(
                                                DefinitionOfDoneItemInput.builder()
                                                        .setText("Test DoD Item 3")
                                                        .setRequired(true)
                                                        .build()))
                                        .build()))
                        .build())
                .build();
    }

    public static UpdateProjectInput getSampleUpdateProjectInput() {
        return UpdateProjectInput.builder()
                .setName("Updated Test Project")
                .setDescription("Updated Test Project")
                .setProjectSettings(ProjectSettingsInput.builder()
                        .setCodeRepositorySettings(CodeRepositorySettingsInput.builder()
                                .setRepositories(
                                        List.of(RepositoryDefinitionInput.builder()
                                                        .setName("Updated Test Repository")
                                                        .setUrl("Updated Test URL")
                                                        .setIcon(IconInput.builder()
                                                                .setMdiIcon("Updated Test Icon")
                                                                .build())
                                                        .build(),
                                                RepositoryDefinitionInput.builder()
                                                        .setName("Updated Test Repository 2")
                                                        .setUrl("Updated Test URL 2")
                                                        .setIcon(IconInput.builder()
                                                                .setPath("Updated Test Path")
                                                                .build())
                                                        .build())
                                )
                                .build())
                        .setImsSettings(ImsSettingsInput.builder()
                                .setImsName("Updated Test IMS")
                                .setImsProjectId("Updated Test IMS Project ID")
                                .setImsIssueTemplateId("Updated Test IMS Issue Template ID")
                                .setImsIcon(IconInput.builder()
                                        .setMdiIcon("Updated Test Icon")
                                        .setPath("Updated Test Path")
                                        .build())
                                .setImsProjectUrl("Updated Test IMS Project URL")
                                .setEffortEstimationFieldName("Updated Test Effort Estimation Field Name")
                                .setSprintFieldName("Updated Test Sprint Field Name")
                                .setIssuePriorities(List.of(
                                        IssuePriorityInput.builder()
                                                .setImsPriorityId("Updated Test IMS Priority ID")
                                                .setIssuePriority(IssuePriority.HIGH)
                                                .build(),
                                        IssuePriorityInput.builder()
                                                .setImsPriorityId("Updated Test IMS Priority ID 2")
                                                .setIssuePriority(IssuePriority.LOW)
                                                .build()))
                                .setIssueStates(List.of(
                                        IssueStateInput.builder()
                                                .setName("Updated Test State")
                                                .setImsStateId("Updated Test IMS State ID")
                                                .setType(IssueStateType.DONE)
                                                .build(),
                                        IssueStateInput.builder()
                                                .setName("Updated Test State 2")
                                                .setImsStateId("Updated Test IMS State ID 2")
                                                .setType(IssueStateType.DONE_SPRINT)
                                                .build()))
                                .build())
                        .setDefinitionOfDone(List.of(
                                DefinitionOfDoneItemInput.builder()
                                        .setText("Updated Test DoD Item")
                                        .setRequired(true)
                                        .build(),
                                DefinitionOfDoneItemInput.builder()
                                        .setText("Updated Test DoD Item 2")
                                        .setRequired(false)
                                        .setImplies(List.of(
                                                DefinitionOfDoneItemInput.builder()
                                                        .setText("Updated Test DoD Item 3")
                                                        .setRequired(true)
                                                        .build()))
                                        .build()))
                        .build())
                .build();
    }

    public static ProjectEntity.ProjectEntityBuilder sampleProjectBuilder() {
        return ProjectEntity.builder()
                .name("Test Project")
                .description("Test Description")
                .projectSettings(ProjectSettingsEntity.builder()
                        .codeRepositorySettings(CodeRepositorySettingsEntity.builder()
                                .setRepositories(
                                        List.of(RepositoryDefinitionEntity.builder()
                                                        .setName("Test Repository")
                                                        .setUrl("Test URL")
                                                        .setIcon(IconEmbeddable.builder()
                                                                .setPath("Test Path")
                                                                .build())
                                                        .build(),
                                                RepositoryDefinitionEntity.builder()
                                                        .setName("Test Repository 2")
                                                        .setUrl("Test URL 2")
                                                        .setIcon(IconEmbeddable.builder()
                                                                .setMdiIcon("Test Icon")
                                                                .build())
                                                        .build()))
                                .build())
                        .imsSettings(ImsSettingsEntity.builder()
                                .imsName("Test IMS")
                                .imsProjectId("Test IMS Project ID")
                                .imsProjectUrl("Test IMS Project URL")
                                .imsIcon(IconEmbeddable.builder()
                                        .setMdiIcon("Test Icon")
                                        .setPath("Test Path")
                                        .build())
                                .imsIssueTemplateId("Test IMS Issue Template ID")
                                .effortEstimationFieldName("Test Effort Estimation Field Name")
                                .sprintFieldName("Test Sprint Field Name")
                                .issuePriorities(new ArrayList<>(List.of(
                                        IssuePriorityEmbeddable.builder()
                                                .imsPriorityId("Test IMS Priority ID")
                                                .issuePriority(IssuePriority.HIGH)
                                                .build(),
                                        IssuePriorityEmbeddable.builder()
                                                .imsPriorityId("Test IMS Priority ID 2")
                                                .issuePriority(IssuePriority.LOW)
                                                .build())))
                                .issueStates(new ArrayList<>(List.of(
                                        IssueStateEmbeddable.builder()
                                                .name("Test State")
                                                .imsStateId("Test IMS State ID")
                                                .type(IssueStateType.SPRINT_BACKLOG)
                                                .build(),
                                        IssueStateEmbeddable.builder()
                                                .name("Test State 2")
                                                .imsStateId("Test IMS State ID 2")
                                                .type(IssueStateType.IN_PROGRESS)
                                                .build())))
                                .build())
                        .definitionOfDone(List.of(
                                DefinitionOfDoneItemEntity.builder()
                                        .text("Test DoD Item")
                                        .required(true)
                                        .build(),
                                DefinitionOfDoneItemEntity.builder()
                                        .text("Test DoD Item 2")
                                        .required(false)
                                        .implies(List.of(
                                                DefinitionOfDoneItemEntity.builder()
                                                        .text("Test DoD Item 3")
                                                        .required(true)
                                                        .build()))
                                        .build()))
                        .build());
    }
}
