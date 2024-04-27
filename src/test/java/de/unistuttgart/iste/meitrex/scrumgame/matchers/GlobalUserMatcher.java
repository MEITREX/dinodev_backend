package de.unistuttgart.iste.meitrex.scrumgame.matchers;

import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserInput;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import org.hamcrest.Matcher;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.each;
import static org.hamcrest.Matchers.*;

public class GlobalUserMatcher {

    private GlobalUserMatcher() {
    }

    public static Matcher<GlobalUser> matchingGlobalUserEntity(GlobalUserEntity entity) {
        return allOf(
                hasProperty("id", equalTo(entity.getId())),
                hasProperty("username", equalTo(entity.getUsername())),
                hasProperty("avatar", equalTo(entity.getAvatar())),
                hasProperty("roles", containsInAnyOrder(
                        each(entity.getRoles(), GlobalUserRoleMatcher::matchingGlobalUserRoleEntity)))
        );
    }

    public static <T> Matcher<T> matchingGlobalUserInput(CreateGlobalUserInput input) {
        return allOf(
                hasProperty("username", equalTo(input.getUsername())),
                hasProperty("avatar", equalTo(input.getAvatar()))
        );
    }

    public static <T> Matcher<T> matchingGlobalUserInput(UpdateGlobalUserInput input) {
        return allOf(
                hasProperty("username", equalTo(input.getUsername())),
                hasProperty("avatar", equalTo(input.getAvatar()))
        );
    }
}
