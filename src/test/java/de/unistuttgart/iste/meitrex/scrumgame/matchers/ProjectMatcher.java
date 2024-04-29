package de.unistuttgart.iste.meitrex.scrumgame.matchers;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.CodeRepositorySettingsEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ImsSettingsEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectSettingsEntity;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.*;

public class ProjectMatcher {
    private ProjectMatcher() {
    }

    public static Matcher<Project> matchingProjectEntity(ProjectEntity project) {
        return allOf(
                hasProperty("id", is(project.getId())),
                hasProperty("name", is(project.getName())),
                hasProperty("description", is(project.getDescription())),
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
                        matchingImsSettingsEntity(projectSettings.getImsSettings()))
        );
    }

    public static <T> Matcher<T> matchingProjectSettingsInput(ProjectSettingsInput projectSettingsInput) {
        return allOf(
                hasProperty("codeRepositorySettings",
                        matchingCodeRepositorySettingsInput(projectSettingsInput.getCodeRepositorySettings())),
                hasProperty("imsSettings",
                        matchingImsSettingsInput(projectSettingsInput.getImsSettings()))
        );
    }

    public static Matcher<ImsSettings> matchingImsSettingsEntity(ImsSettingsEntity imsSettings) {
        return allOf(
                hasProperty("imsName", is(imsSettings.getImsName())),
                hasProperty("imsProjectId", is(imsSettings.getImsProjectId()))
        );
    }

    public static Matcher<CodeRepositorySettings> matchingCodeRepositorySettingsEntity(CodeRepositorySettingsEntity codeRepositorySettings) {
        return allOf(
                hasProperty("codeRepositoryName",
                        is(codeRepositorySettings.getCodeRepositoryName()))
        );
    }

    public static <T> Matcher<T> matchingCodeRepositorySettingsInput(CodeRepositorySettingsInput codeRepositorySettingsInput) {
        return allOf(
                hasProperty("codeRepositoryName", is(codeRepositorySettingsInput.getCodeRepositoryName()))
        );
    }

    public static <T> Matcher<T> matchingImsSettingsInput(ImsSettingsInput imsSettingsInput) {
        return allOf(
                hasProperty("imsName", is(imsSettingsInput.getImsName())),
                hasProperty("imsProjectId", is(imsSettingsInput.getImsProjectId()))
        );
    }
}
