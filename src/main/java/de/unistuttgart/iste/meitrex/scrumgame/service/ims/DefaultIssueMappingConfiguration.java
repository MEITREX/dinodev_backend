package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.generated.dto.ImsSettings;
import de.unistuttgart.iste.meitrex.scrumgame.ims.IssueMappingConfiguration;
import de.unistuttgart.iste.meitrex.scrumgame.ims.IssueStateConverter;
import de.unistuttgart.iste.meitrex.scrumgame.ims.UserIdMapping;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class DefaultIssueMappingConfiguration implements IssueMappingConfiguration {

    private final ImsSettings imsSettings;

    @Override
    public IssueStateConverter issueStateConverter() {
        return new IssueStateConverter(imsSettings.getIssueStates());
    }

    @Override
    public UserIdMapping userIdMapping() {
        return UserIdMapping.identity();
    }

    @Override
    public String getSprintFieldName() {
        return "Sprint"; // todo make configurable
    }

    @Override
    public String getEstimationTemplateFieldName() {
        return "Estimated Effort";
    }
}
