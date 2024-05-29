package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.common.graphqlclient.GraphQlRequestExecutor;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsConnector;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius.GropiusConnector;
import de.unistuttgart.iste.meitrex.scrumgame.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GropiusConnectorFactory implements ImsConnectorFactory {

    private final ProjectService         projectService;
    private final GraphQlRequestExecutor graphQlRequestExecutor;

    @Value("${gropius.frontend.url}")
    private String gropiusFrontendUrl;

    @Override
    public ImsConnector getImsConnectorForProject(UUID projectId) {
        return getImsConnectorForProject(projectService.getProjectOrThrow(projectId));
    }

    @Override
    public ImsConnector getImsConnectorForProject(Project project) {
        return new GropiusConnector(graphQlRequestExecutor, DefaultIssueMappingConfiguration.of(project,
                gropiusFrontendUrl));
    }

}
