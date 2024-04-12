package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SprintController {

    private final SprintService sprintService;

    @SchemaMapping(typeName = "ProjectMutation", field = "createSprint")
    public Sprint createSprint(ProjectMutation projectMutation,
                               @Argument CreateSprintInput input,
                               @ContextValue UUID userId) {
        return sprintService.createSprint(projectMutation.getId(), input, userId);
    }
}
