package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.GlobalUserRoleService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GlobalUserRoleController {

    private final GlobalUserRoleService globalUserRoleService;

    @QueryMapping
    public List<GlobalUserRole> globalUserRoles() {
        return globalUserRoleService.getAllGlobalUserRoles();
    }

    @QueryMapping
    @Nullable
    public GlobalUserRole globalUserRole(@Argument String name) {
        return globalUserRoleService.findGlobalUserRole(name).orElse(null);
    }

    @MutationMapping
    public GlobalUserRole createGlobalUserRole(@Argument CreateGlobalUserRoleInput input) {
        return globalUserRoleService.createGlobalUserRole(input);
    }

    @MutationMapping
    public GlobalUserRole updateGlobalUserRole(@Argument String name,
                                               @Argument UpdateGlobalUserRoleInput input) {
        return globalUserRoleService.updateGlobalUserRole(name, input);
    }

    @MutationMapping
    public boolean deleteGlobalUserRole(@Argument String name) {
        return globalUserRoleService.deleteGlobalUserRole(name);
    }

}
