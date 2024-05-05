package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.generated.dto.ImsSettings;
import de.unistuttgart.iste.meitrex.generated.dto.IssuePriorityConfiguration;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.scrumgame.ims.IssuePriorityMapping;
import de.unistuttgart.iste.meitrex.scrumgame.ims.IssueStateConverter;
import de.unistuttgart.iste.meitrex.scrumgame.ims.UserIdMapping;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius.GropiusIssueMappingConfiguration;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor(staticName = "of")
public class DefaultIssueMappingConfiguration implements GropiusIssueMappingConfiguration {

    private final Project project;

    @Override
    public UUID getScrumGameProjectId() {
        return project.getId();
    }

    @Override
    public String getImsProjectId() {
        return getImsSettings().getImsProjectId();
    }

    private ImsSettings getImsSettings() {
        return project.getProjectSettings().getImsSettings();
    }

    public IssueStateConverter issueStateConverter() {
        return new IssueStateConverter(getImsSettings().getIssueStates());
    }

    @Override
    public UserIdMapping userIdMapping() {
        return UserIdMapping.identity();
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
    public IssuePriorityMapping issuePriorityMapping() {
        return new IssuePriorityMapping(
                getImsSettings().getIssuePriorities().stream()
                        .collect(Collectors.toMap(
                                IssuePriorityConfiguration::getImsPriorityId,
                                IssuePriorityConfiguration::getIssuePriority)));
    }
}
