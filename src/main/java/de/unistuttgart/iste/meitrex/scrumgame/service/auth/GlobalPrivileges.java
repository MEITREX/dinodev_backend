package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Contains all global privileges.
 * This class is used to avoid magic strings in the Spring Expression Language,
 * which cannot access the enum values directly,
 * using {@code @globalPrivileges.UPDATE_USER} instead of {@code 'UPDATE_USER'}.
 */
@Component
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class GlobalPrivileges {

    public static final GlobalPrivilege UPDATE_USER = GlobalPrivilege.UPDATE_USER;
    public static final GlobalPrivilege LIST_USERS = GlobalPrivilege.LIST_USERS;
    public static final GlobalPrivilege CREATE_PROJECT = GlobalPrivilege.CREATE_PROJECT;
}
