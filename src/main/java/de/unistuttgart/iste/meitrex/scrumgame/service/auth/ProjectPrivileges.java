package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Contains all project privileges.
 * This class is used to avoid magic strings in the Spring Expression Language,
 * which cannot access the enum values directly,
 * using {@code @projectPrivileges.UPDATE_PROJECT} instead of {@code 'UPDATE_PROJECT'}.
 */
@Component
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ProjectPrivileges {

    public static final ProjectPrivilege UPDATE_PROJECT = ProjectPrivilege.UPDATE_PROJECT;
    public static final ProjectPrivilege DELETE_PROJECT = ProjectPrivilege.DELETE_PROJECT;
    public static final ProjectPrivilege CREATE_SPRINT = ProjectPrivilege.CREATE_SPRINT;
    public static final ProjectPrivilege UPDATE_SPRINT = ProjectPrivilege.UPDATE_SPRINT;
    public static final ProjectPrivilege DELETE_SPRINT = ProjectPrivilege.DELETE_SPRINT;
    public static final ProjectPrivilege CREATE_SHOP_ITEM = ProjectPrivilege.CREATE_SHOP_ITEM;
    public static final ProjectPrivilege UPDATE_SHOP_ITEM = ProjectPrivilege.UPDATE_SHOP_ITEM;
    public static final ProjectPrivilege DELETE_SHOP_ITEM = ProjectPrivilege.DELETE_SHOP_ITEM;
    public static final ProjectPrivilege CREATE_USER = ProjectPrivilege.CREATE_USER;
    public static final ProjectPrivilege UPDATE_USER = ProjectPrivilege.UPDATE_USER;
    public static final ProjectPrivilege DELETE_USER = ProjectPrivilege.DELETE_USER;
    public static final ProjectPrivilege CREATE_ROLE = ProjectPrivilege.CREATE_ROLE;
    public static final ProjectPrivilege UPDATE_ROLE = ProjectPrivilege.UPDATE_ROLE;
    public static final ProjectPrivilege DELETE_ROLE = ProjectPrivilege.DELETE_ROLE;

}
