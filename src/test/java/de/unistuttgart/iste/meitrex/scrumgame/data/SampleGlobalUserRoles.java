package de.unistuttgart.iste.meitrex.scrumgame.data;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;

import java.util.List;

public class SampleGlobalUserRoles {

    private SampleGlobalUserRoles() {
    }

    public static GlobalUserRoleEntity.GlobalUserRoleEntityBuilder sampleGlobalUserRoleBuilder() {
        return GlobalUserRoleEntity.builder()
                .name("Test Role")
                .globalPrivileges(List.of(
                        GlobalPrivilege.CREATE_PROJECT,
                        GlobalPrivilege.READ_USER_PRIVATE_INFO));
    }
}
