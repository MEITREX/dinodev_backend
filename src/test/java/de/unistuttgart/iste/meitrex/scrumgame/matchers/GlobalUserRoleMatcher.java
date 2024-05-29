package de.unistuttgart.iste.meitrex.scrumgame.matchers;

import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserRoleInput;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserRoleInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.*;


public class GlobalUserRoleMatcher {
    private GlobalUserRoleMatcher() {
    }

    public static <T> Matcher<T> matchingGlobalUserRoleEntity(GlobalUserRoleEntity globalUserRole) {
        return allOf(
                hasProperty("name", is(globalUserRole.getName())),
                hasProperty("globalPrivileges",
                        containsInAnyOrder(globalUserRole.getGlobalPrivileges().toArray()))
        );
    }

    public static <T> Matcher<T> matchingGlobalUserRoleInput(CreateGlobalUserRoleInput globalUserRoleInput) {
        return allOf(
                hasProperty("name", is(globalUserRoleInput.getName())),
                hasProperty("globalPrivileges",
                        containsInAnyOrder(globalUserRoleInput.getGlobalPrivileges().toArray()))
        );
    }

    public static <T> Matcher<T> matchingGlobalUserRoleInput(UpdateGlobalUserRoleInput updateGlobalUserRoleInput) {
        return allOf(
                hasProperty("globalPrivileges",
                        containsInAnyOrder(updateGlobalUserRoleInput.getGlobalPrivileges().toArray()))
        );
    }
}
