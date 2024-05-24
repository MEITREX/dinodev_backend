package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsConnector;

import java.util.*;

public interface ImsConnectorFactory {

    ImsConnector getImsConnectorForProject(UUID projectId);

    ImsConnector getImsConnectorForProject(Project project);
}
