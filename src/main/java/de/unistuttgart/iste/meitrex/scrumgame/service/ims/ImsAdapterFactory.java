package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsAdapter;

import java.util.*;

public interface ImsAdapterFactory {

    ImsAdapter getImsAdapterForProject(UUID projectId);

    ImsAdapter getImsAdapterForProject(Project project);
}
