package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserInProjectController {


    @SchemaMapping(typeName = "GlobalUser", field = "userInProject")
    public UserInProject userInProject(GlobalUser globalUser, @Argument UUID projectId) {
        return null; // todo
    }

    @SchemaMapping(typeName = "Project", field = "users")
    public List<UserInProject> usersInProject(Project project) {
        return null; // todo
    }

}
