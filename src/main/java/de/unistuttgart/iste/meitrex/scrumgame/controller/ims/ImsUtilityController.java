package de.unistuttgart.iste.meitrex.scrumgame.controller.ims;

import de.unistuttgart.iste.meitrex.generated.dto.ImsSpecificData;
import de.unistuttgart.iste.meitrex.generated.dto.ImsUser;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsUtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ImsUtilityController {

    private final ImsUtilityService imsUtilityService;

    @SchemaMapping
    public ImsSpecificData imsSpecificData(Project project) {
        return imsUtilityService.getImsSpecificData(project.getId());
    }

    @SchemaMapping
    public ImsUser currentUser(ImsSpecificData imsSpecificData) {
        return imsUtilityService.getCurrentUser();
    }
}
