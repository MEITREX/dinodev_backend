package de.unistuttgart.iste.meitrex.scrumgame.api.user;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.GlobalPrivileges;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.causedBy;
import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.containsError;
import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleGlobalUserRoles.sampleGlobalUserRoleBuilder;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleGlobalUsers.sampleGlobalUserEntityBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@GraphQlApiTest
@ActiveProfiles("test")
class MutationGrantRevokeRoleTest {

    @Autowired
    private GlobalUserRepository     globalUserRepository;
    @Autowired
    private GlobalUserRoleRepository globalUserRoleRepository;

    @MockBean
    private AuthService authService;

    @Test
    void testGrantRole(GraphQlTester graphQlTester) {
        // arrange
        String roleName = "Test Role";

        when(authService.hasPrivilege(GlobalPrivileges.CHANGE_ROLES)).thenReturn(true);
        when(authService.hasPrivilegesOfGlobalRole(roleName)).thenReturn(true);

        UUID userId = UUID.randomUUID();

        globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().name(roleName).build());
        globalUserRepository.save(sampleGlobalUserEntityBuilder().id(userId).build());

        String mutation = getGrantRoleMutation();

        // act
        GlobalUser result = graphQlTester.document(mutation)
                .variable("userId", userId)
                .variable("roleName", roleName)
                .execute()
                .path("grantRole")
                .entity(GlobalUser.class)
                .get();

        // assert
        assertThat(result.getRoles(), hasItem(hasProperty("name", equalTo(roleName))));

        GlobalUserEntity userEntity = globalUserRepository.findById(result.getId()).orElseThrow();
        assertThat(userEntity.getRoles(), hasItem(hasProperty("name", equalTo(roleName))));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.CHANGE_ROLES);
        verify(authService, atLeastOnce()).hasPrivilegesOfGlobalRole(roleName);
    }

    @Test
    void testRevokeRole(GraphQlTester graphQlTester) {
        // arrange
        String roleName = "Test Role";

        when(authService.hasPrivilege(GlobalPrivileges.CHANGE_ROLES)).thenReturn(true);
        when(authService.hasPrivilegesOfGlobalRole(roleName)).thenReturn(true);

        UUID userId = UUID.randomUUID();

        GlobalUserRoleEntity role = globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().name(roleName).build());
        globalUserRepository.save(sampleGlobalUserEntityBuilder().id(userId)
                .roles(new ArrayList<>(List.of(role)))
                .build());

        String mutation = getRevokeRoleMutation();

        // act
        GlobalUser result = graphQlTester.document(mutation)
                .variable("userId", userId)
                .variable("roleName", roleName)
                .execute()
                .path("revokeRole")
                .entity(GlobalUser.class)
                .get();

        // assert
        assertThat(result.getRoles(), not(hasItem(hasProperty("name", equalTo(roleName)))));

        GlobalUserEntity userEntity = globalUserRepository.findById(result.getId()).orElseThrow();
        assertThat(userEntity.getRoles(), not(hasItem(hasProperty("name", equalTo(roleName)))));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.CHANGE_ROLES);
        verify(authService, atLeastOnce()).hasPrivilegesOfGlobalRole(roleName);
    }

    @Test
    void testGrantRevokeRoleWithoutPrivilege(GraphQlTester graphQlTester) {
        // arrange
        String roleName = "Test Role";

        when(authService.hasPrivilege(GlobalPrivilege.CHANGE_ROLES)).thenReturn(false);
        when(authService.hasPrivilegesOfGlobalRole(roleName)).thenReturn(true);

        UUID userId = UUID.randomUUID();

        globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().name(roleName).build());
        globalUserRepository.save(sampleGlobalUserEntityBuilder().id(userId).build());

        String mutation = getGrantRoleMutation();

        // act & assert
        graphQlTester.document(mutation)
                .variable("userId", userId)
                .variable("roleName", roleName)
                .execute()
                .errors()
                .satisfy(errors -> assertThat(errors, containsError(causedBy(AuthorizationDeniedException.class))));

        String revokeMutation = getRevokeRoleMutation();

        graphQlTester.document(revokeMutation)
                .variable("userId", userId)
                .variable("roleName", roleName)
                .execute()
                .errors()
                .satisfy(errors -> assertThat(errors, containsError(causedBy(AuthorizationDeniedException.class))));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivilege.CHANGE_ROLES);
    }

    private String getGrantRoleMutation() {
        return gql("""
                mutation($userId: UUID!, $roleName: String!) {
                    grantRole(userId: $userId, roleName: $roleName) {
                        id
                        roles {
                            name
                        }
                    }
                }
                """);
    }

    private String getRevokeRoleMutation() {
        return gql("""
                mutation($userId: UUID!, $roleName: String!) {
                    revokeRole(userId: $userId, roleName: $roleName) {
                        id
                        roles {
                            name
                        }
                    }
                }
                """);
    }
}
