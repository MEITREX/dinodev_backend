package de.unistuttgart.iste.meitrex.scrumgame.controller.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.generated.dto.RetrospectiveMeeting;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RetrospectiveMeetingController {

    @SchemaMapping(typeName = "Project", field = "activeRetrospectiveMeeting")
    public RetrospectiveMeeting activeRetrospectiveMeeting(Project project) {
        return null; // TODO implement
    }
}
