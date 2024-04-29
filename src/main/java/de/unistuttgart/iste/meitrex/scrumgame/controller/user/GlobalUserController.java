package de.unistuttgart.iste.meitrex.scrumgame.controller.user;

import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserInput;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserInput;
import de.unistuttgart.iste.meitrex.generated.dto.UserInProject;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.GlobalUserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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
        return userService.findGlobalUser(id).orElse(null);
    }

    @QueryMapping
    @Nullable
    public GlobalUser currentUser() {
        return userService.findCurrentUser().orElse(null);
    }

    @SchemaMapping
    public GlobalUser user(UserInProject userInProject) {
        return userService.getGlobalUserOrThrow(userInProject.getUserId());
    }

    @MutationMapping
    public GlobalUser grantRole(@Argument UUID userId, @Argument String roleName) {
        return userService.grantRole(userId, roleName);
    }

    @MutationMapping
    public GlobalUser revokeRole(@Argument UUID userId, @Argument String roleName) {
        return userService.revokeRole(userId, roleName);
    }

    @MutationMapping
    public GlobalUser register(@Argument CreateGlobalUserInput input) {
        return userService.registerNewUser(input);
    }

    @MutationMapping
    public GlobalUser updateGlobalUser(@Argument UUID id, @Argument UpdateGlobalUserInput input) {
        return userService.updateGlobalUser(id, input);
    }
}
