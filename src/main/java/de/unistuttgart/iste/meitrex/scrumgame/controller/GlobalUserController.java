package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.GlobalUserService;
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
        return userService.getAllGlobalUserProfiles();
    }

    @QueryMapping
    public GlobalUser globalUser(@Argument UUID id) {
        return userService.getGlobalUserProfile(id);
    }

    @QueryMapping
    public GlobalUser currentUser() {
        return userService.getCurrentUser();
    }

    @MutationMapping
    public GlobalUser register(@Argument CreateUserInput input) {
        return userService.registerNewUser(input);
    }

    @MutationMapping
    public GlobalUser updateGlobalUserProfile(@Argument UUID id,
                                              @Argument UpdateUserInput input) {
        return userService.updateGlobalUserProfile(id, input);
    }

    @SchemaMapping(typeName = "UserInProject", field = "user")
    public GlobalUser user(UserInProject userInProject) {
        return userService.getGlobalUserProfile(userInProject.getUserId());
    }


    @MutationMapping
    public GlobalUser grantRole(@Argument("userId") UUID targetUserId,
                                @Argument UUID roleId) {
        return userService.grantGlobalRole(targetUserId, roleId);
    }

}
