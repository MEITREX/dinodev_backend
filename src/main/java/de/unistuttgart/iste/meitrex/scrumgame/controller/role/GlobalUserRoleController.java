package de.unistuttgart.iste.meitrex.scrumgame.controller.role;

import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserRoleInput;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUserRole;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserRoleInput;
import de.unistuttgart.iste.meitrex.scrumgame.service.role.GlobalUserRoleService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GlobalUserRoleController {

    private final GlobalUserRoleService globalUserRoleService;

    /* Query mappings */

    @QueryMapping
    public List<GlobalUserRole> globalUserRoles() {
        return globalUserRoleService.getAllGlobalUserRoles();
    }

    @QueryMapping
    @Nullable
    public GlobalUserRole globalUserRole(@Argument String name) {
        return globalUserRoleService.findGlobalUserRole(name).orElse(null);
    }

    /* Schema mappings */
    // none

    /* Mutation mappings */

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
