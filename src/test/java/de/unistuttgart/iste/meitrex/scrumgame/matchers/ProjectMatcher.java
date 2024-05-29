package de.unistuttgart.iste.meitrex.scrumgame.matchers;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.*;
import org.hamcrest.Matcher;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.each;
import static org.hamcrest.Matchers.*;

/**
 * Contains matchers for Project related entities and inputs.
 */
public class ProjectMatcher {

    private ProjectMatcher() {
    }

    public static Matcher<Project> matchingProjectEntity(ProjectEntity project) {
        return allOf(
                hasProperty("id", is(project.getId())),
                hasProperty("name", is(project.getName())),
                hasProperty("description", is(project.getDescription())),
                hasProperty("currentSprintNumber", is(project.getCurrentSprintNumber())),
                hasProperty("projectSettings",
                        matchingProjectSettingsEntity(project.getProjectSettings()))
        );
    }

    public static <T> Matcher<T> matchingProjectInput(CreateProjectInput projectInput) {
        return allOf(
                hasProperty("name", is(projectInput.getName())),
                hasProperty("description", is(projectInput.getDescription())),
                hasProperty("projectSettings",
                        matchingProjectSettingsInput(projectInput.getProjectSettings()))
        );
    }

    public static <T> Matcher<T> matchingUpdateProjectInput(UpdateProjectInput updateProjectInput) {
        return allOf(
                hasProperty("name", is(updateProjectInput.getName())),
                hasProperty("description", is(updateProjectInput.getDescription())),
                hasProperty("projectSettings",
                        matchingProjectSettingsInput(updateProjectInput.getProjectSettings()))
        );
    }

    public static Matcher<ProjectSettings> matchingProjectSettingsEntity(ProjectSettingsEntity projectSettings) {
        return allOf(
                hasProperty("codeRepositorySettings",
                        matchingCodeRepositorySettingsEntity(projectSettings.getCodeRepositorySettings())),
                hasProperty("imsSettings",
                        matchingImsSettingsEntity(projectSettings.getImsSettings())),
                hasProperty("definitionOfDone",
                        containsInAnyOrder(each(projectSettings.getDefinitionOfDone(),
                                ProjectMatcher::matchingDodEntity)))
        );
    }

    public static <T> Matcher<T> matchingProjectSettingsInput(ProjectSettingsInput projectSettingsInput) {
        return allOf(
                hasProperty("codeRepositorySettings",
                        matchingCodeRepositorySettingsInput(projectSettingsInput.getCodeRepositorySettings())),
                hasProperty("imsSettings",
                        matchingImsSettingsInput(projectSettingsInput.getImsSettings())),
                hasProperty("definitionOfDone",
                        containsInAnyOrder(each(projectSettingsInput.getDefinitionOfDone(),
                                ProjectMatcher::matchingDodInput)))
        );
    }

    public static Matcher<ImsSettings> matchingImsSettingsEntity(ImsSettingsEntity imsSettings) {
        return allOf(
                hasProperty("imsName", is(imsSettings.getImsName())),
                hasProperty("imsProjectId", is(imsSettings.getImsProjectId())),
                hasProperty("issueStates",
                        containsInAnyOrder(each(imsSettings.getIssueStates(),
                                ProjectMatcher::matchingIssueStateEntity))),
                hasProperty("imsIssueTemplateId", is(imsSettings.getImsIssueTemplateId())),
                hasProperty("effortEstimationFieldName", is(imsSettings.getEffortEstimationFieldName())),
                hasProperty("sprintFieldName", is(imsSettings.getSprintFieldName())),
                hasProperty("partOfRelationId", is(imsSettings.getPartOfRelationId())),
                hasProperty("issuePriorities",
                        containsInAnyOrder(each(imsSettings.getIssuePriorities(),
                                ProjectMatcher::matchingIssuePriorityEntity)))

        );
    }

    public static Matcher<IssueState> matchingIssueStateEntity(IssueStateEmbeddable issueState) {
        return allOf(
                hasProperty("name", is(issueState.getName())),
                hasProperty("imsStateId", is(issueState.getImsStateId())),
                hasProperty("type", is(issueState.getType()))
        );
    }

    public static Matcher<CodeRepositorySettings> matchingCodeRepositorySettingsEntity(CodeRepositorySettingsEntity codeRepositorySettings) {
        return allOf(
                hasProperty("repositories",
                        containsInAnyOrder(each(codeRepositorySettings.getRepositories(),
                                ProjectMatcher::matchingRepositoryDefinitionEntity)))
        );
    }

    public static Matcher<RepositoryDefinition> matchingRepositoryDefinitionEntity(RepositoryDefinitionEntity repositoryDefinition) {
        return allOf(
                hasProperty("name", is(repositoryDefinition.getName())),
                hasProperty("url", is(repositoryDefinition.getUrl())),
                hasProperty("icon", matchingIcon(repositoryDefinition.getIcon()))
        );
    }

    public static Matcher<Icon> matchingIcon(IconEmbeddable icon) {
        return allOf(
                hasProperty("mdiIcon", is(icon.getMdiIcon())),
                hasProperty("path", is(icon.getPath()))
        );
    }

    public static <T> Matcher<T> matchingCodeRepositorySettingsInput(CodeRepositorySettingsInput codeRepositorySettingsInput) {
        return allOf(
                hasProperty("repositories",
                        containsInAnyOrder(each(codeRepositorySettingsInput.getRepositories(),
                                ProjectMatcher::matchingRepositoryDefinitionInput))
                ));
    }

    public static <T> Matcher<T> matchingRepositoryDefinitionInput(RepositoryDefinitionInput repositoryDefinitionInput) {
        return allOf(
                hasProperty("name", is(repositoryDefinitionInput.getName())),
                hasProperty("url", is(repositoryDefinitionInput.getUrl())),
                hasProperty("icon", matchingIconInput(repositoryDefinitionInput.getIcon()))
        );
    }

    public static <T> Matcher<T> matchingIconInput(IconInput iconInput) {
        return allOf(
                hasProperty("mdiIcon", is(iconInput.getMdiIcon())),
                hasProperty("path", is(iconInput.getPath()))
        );
    }

    public static <T> Matcher<T> matchingImsSettingsInput(ImsSettingsInput imsSettingsInput) {
        return allOf(
                hasProperty("imsName", is(imsSettingsInput.getImsName())),
                hasProperty("imsProjectId", is(imsSettingsInput.getImsProjectId())),
                hasProperty("issueStates",
                        containsInAnyOrder(each(imsSettingsInput.getIssueStates(),
                                ProjectMatcher::matchingIssueStateInput))),
                hasProperty("imsIssueTemplateId", is(imsSettingsInput.getImsIssueTemplateId())),
                hasProperty("effortEstimationFieldName", is(imsSettingsInput.getEffortEstimationFieldName())),
                hasProperty("sprintFieldName", is(imsSettingsInput.getSprintFieldName())),
                hasProperty("partOfRelationId", is(imsSettingsInput.getPartOfRelationId())),
                hasProperty("issuePriorities",
                        containsInAnyOrder(each(imsSettingsInput.getIssuePriorities(),
                                ProjectMatcher::matchingIssuePriorityInput)))
        );
    }

    public static <T> Matcher<T> matchingIssueStateInput(IssueStateInput issueStateInput) {
        return allOf(
                hasProperty("name", is(issueStateInput.getName())),
                hasProperty("imsStateId", is(issueStateInput.getImsStateId())),
                hasProperty("type", is(issueStateInput.getType()))
        );
    }

    public static <T> Matcher<T> matchingDodEntity(DefinitionOfDoneItemEntity dodItem) {
        return allOf(
                hasProperty("text", is(dodItem.getText())),
                hasProperty("required", is(dodItem.isRequired())),
                hasProperty("implies",
                        containsInAnyOrder(each(dodItem.getImplies(), ProjectMatcher::matchingDodEntity)))
        );
    }

    public static <T> Matcher<T> matchingDodInput(DefinitionOfDoneItemInput dodItem) {
        return allOf(
                hasProperty("text", is(dodItem.getText())),
                hasProperty("required", is(dodItem.getRequired())),
                hasProperty("implies",
                        containsInAnyOrder(each(dodItem.getImplies(), ProjectMatcher::matchingDodInput)))
        );
    }

    public static Matcher<IssuePriority> matchingIssuePriorityEntity(IssuePriorityEmbeddable issuePriority) {
        return allOf(
                hasProperty("issuePriority", is(issuePriority.getIssuePriority())),
                hasProperty("imsPriorityId", is(issuePriority.getImsPriorityId()))
        );
    }

    public static <T> Matcher<T> matchingIssuePriorityInput(IssuePriorityInput issuePriorityInput) {
        return allOf(
                hasProperty("issuePriority", is(issuePriorityInput.getIssuePriority())),
                hasProperty("imsPriorityId", is(issuePriorityInput.getImsPriorityId()))
        );
    }
}
