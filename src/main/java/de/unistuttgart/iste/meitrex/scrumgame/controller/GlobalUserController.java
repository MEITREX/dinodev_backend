package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.GlobalUserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class GlobalUserController {

    private final GlobalUserService userService;

    @QueryMapping
    public List<GlobalUser> globalUsers() {
        return userService.getAllGlobalUsers();
    }

    @QueryMapping
    @Nullable
    public GlobalUser globalUser(@Argument UUID id) {
        return userService.getGlobalUser(id).orElse(null);
    }

    @QueryMapping
    @Nullable
    public GlobalUser currentUser() {
        return userService.getCurrentUser().orElse(null);
    }

    @MutationMapping
    public GlobalUser register(@Argument CreateGlobalUserInput input) {
        return userService.registerNewUser(input);
    }

    @MutationMapping
    public GlobalUser updateGlobalUser(@Argument UUID id,
                                       @Argument UpdateGlobalUserInput input) {
        return userService.updateGlobalUser(id, input);
    }

    @SchemaMapping(typeName = "UserInProject", field = "user")
    public GlobalUser user(UserInProject userInProject) {
        return userService.getGlobalUserOrThrow(userInProject.getUserId());
    }

    @MutationMapping
    public GlobalUser grantRole(@Argument UUID userId,
                                @Argument String roleName) {
        return userService.grantRole(userId, roleName);
    }

}
