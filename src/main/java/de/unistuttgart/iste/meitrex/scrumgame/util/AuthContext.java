package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;

import java.util.Arrays;
import java.util.UUID;

public interface AuthContext {

    boolean hasProjectPrivilege(ProjectPrivilege privilege);

    boolean hasGlobalPrivilege(GlobalPrivilege privilege);

    boolean isMeetingLeader();

    UUID getUserId();

    default boolean hasProjectPrivilege(String... privileges) {
        return Arrays.stream(privileges)
                .map(ProjectPrivilege::valueOf)
                .allMatch(this::hasProjectPrivilege);
    }

    default boolean hasGlobalPrivilege(String... privileges) {
        return Arrays.stream(privileges)
                .map(GlobalPrivilege::valueOf)
                .allMatch(this::hasGlobalPrivilege);
    }

}
