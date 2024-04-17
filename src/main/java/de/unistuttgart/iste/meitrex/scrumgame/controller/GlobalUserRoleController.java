package de.unistuttgart.iste.meitrex.scrumgame.controller;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalUserRole;
import de.unistuttgart.iste.meitrex.scrumgame.service.GlobalUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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

}
