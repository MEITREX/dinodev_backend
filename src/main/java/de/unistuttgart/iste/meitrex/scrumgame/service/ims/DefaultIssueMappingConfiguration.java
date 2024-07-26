package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.generated.dto.ImsSettings;
import de.unistuttgart.iste.meitrex.generated.dto.IssuePriorityConfiguration;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.scrumgame.ims.gropius.config.GropiusIssueMappingConfiguration;
import de.unistuttgart.iste.meitrex.scrumgame.ims.gropius.config.IssuePriorityMapping;
import de.unistuttgart.iste.meitrex.scrumgame.ims.gropius.config.IssueStateMapping;
import de.unistuttgart.iste.meitrex.scrumgame.ims.gropius.config.IssueTypeMapping;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.*;

@RequiredArgsConstructor(staticName = "of")
public class DefaultIssueMappingConfiguration implements GropiusIssueMappingConfiguration {

    private final Project project;
    private final String gropiusUrl;

    @Override
    public UUID getDinoDevProjectId() {
        return project.getId();
    }

    @Override
    public String getImsProjectId() {
        return getImsSettings().getImsProjectId();
    }

    private ImsSettings getImsSettings() {
        return project.getProjectSettings().getImsSettings();
    }

    public IssueStateMapping getIssueStateConverter() {
        return new IssueStateMapping(getImsSettings().getIssueStates());
    }

    @Override
    public String getSprintFieldName() {
        return getImsSettings().getSprintFieldName();
    }

    @Override
    public String getEstimationTemplateFieldName() {
        return getImsSettings().getEffortEstimationFieldName();
    }

    @Override
    public String getIssueTemplateId() {
        return getImsSettings().getImsIssueTemplateId();
    }

    @Override
    public IssuePriorityMapping getIssuePriorityMapping() {
        return new IssuePriorityMapping(
                getImsSettings().getIssuePriorities().stream()
                        .collect(Collectors.toMap(
                                IssuePriorityConfiguration::getImsPriorityId,
                                IssuePriorityConfiguration::getIssuePriority)));
    }

    @Override
    public IssueTypeMapping getIssueTypeMapping() {
        return new IssueTypeMapping(
                getImsSettings().getIssueTypes().stream()
                        .map(type -> new IssueTypeMapping.IssueTypeConfiguration(type.getImsTypeId(), type.getName()))
                        .toList());
    }

    @Override
    public String getGropiusBaseUrl() {
        return Objects.requireNonNull(gropiusUrl, "Gropius URL must be set in application.properties");
    }
}
