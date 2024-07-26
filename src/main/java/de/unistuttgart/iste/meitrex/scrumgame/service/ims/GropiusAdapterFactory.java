package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.common.graphqlclient.GraphQlRequestExecutor;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsAdapter;
import de.unistuttgart.iste.meitrex.scrumgame.ims.gropius.GropiusAdapter;
import de.unistuttgart.iste.meitrex.scrumgame.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GropiusAdapterFactory implements ImsAdapterFactory {

    private final ProjectService         projectService;
    private final GraphQlRequestExecutor graphQlRequestExecutor;

    @Value("${gropius.frontend.url}")
    private String gropiusFrontendUrl;

    @Override
    public ImsAdapter getImsAdapterForProject(UUID projectId) {
        return getImsAdapterForProject(projectService.getProjectOrThrow(projectId));
    }

    @Override
    public ImsAdapter getImsAdapterForProject(Project project) {
        return new GropiusAdapter(graphQlRequestExecutor, DefaultIssueMappingConfiguration.of(project,
                gropiusFrontendUrl));
    }

}
