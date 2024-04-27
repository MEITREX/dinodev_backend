package de.unistuttgart.iste.meitrex.scrumgame.api.role;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUserRole;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserRoleInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.GlobalPrivileges;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.causedBy;
import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.containsError;
import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleGlobalUserRoles.sampleGlobalUserRoleBuilder;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.GlobalUserRoleMatcher.matchingGlobalUserRoleInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;

@GraphQlApiTest
@ActiveProfiles("test")
class MutationUpdateGlobalUserRuleTest {

    @Autowired
    private GlobalUserRoleRepository globalUserRoleRepository;

    @MockBean
    private AuthService authService;

    @Test
    void testUpdateGlobalUserRole(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.CHANGE_ROLES)).thenReturn(true);

        GlobalUserRoleEntity role = globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().build());
        UpdateGlobalUserRoleInput input = getSampleUpdateGlobalUserRoleInput();
        String mutation = getUpdateGlobalUserRoleMutation();

        // act
        GlobalUserRole result = graphQlTester.document(mutation)
                .variable("name", role.getName())
                .variable("input", input)
                .execute()
                .path("updateGlobalUserRole")
                .entity(GlobalUserRole.class)
                .get();

        // assert
        assertThat(result, matchingGlobalUserRoleInput(input));

        GlobalUserRoleEntity roleEntity = globalUserRoleRepository.findById(result.getName()).orElseThrow();
        assertThat(roleEntity, matchingGlobalUserRoleInput(input));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.CHANGE_ROLES);
    }

    @Test
    void testUpdateGlobalUserRoleWithoutPrivilege(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.CHANGE_ROLES)).thenReturn(false);

        GlobalUserRoleEntity role = globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().build());
        UpdateGlobalUserRoleInput input = getSampleUpdateGlobalUserRoleInput();
        String mutation = getUpdateGlobalUserRoleMutation();

        // act
        graphQlTester.document(mutation)
                .variable("name", role.getName())
                .variable("input", input)
                .execute()
                .errors()
                .satisfy(errors -> assertThat(errors, containsError(causedBy(AccessDeniedException.class))));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.CHANGE_ROLES);
    }

    @Test
    void testUpdateAdminUserRole(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.CHANGE_ROLES)).thenReturn(true);

        GlobalUserRoleEntity role = globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().name("ADMIN").build());
        UpdateGlobalUserRoleInput input = getSampleUpdateGlobalUserRoleInput();
        String mutation = getUpdateGlobalUserRoleMutation();

        // act
        graphQlTester.document(mutation)
                .variable("name", role.getName())
                .variable("input", input)
                .execute()
                .errors()
                .satisfy(errors -> assertThat(errors, containsError(causedBy(AccessDeniedException.class))));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.CHANGE_ROLES);
    }

    @Test
    void testUpdateGlobalUserRoleNotFound(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.CHANGE_ROLES)).thenReturn(true);

        UpdateGlobalUserRoleInput input = getSampleUpdateGlobalUserRoleInput();
        String mutation = getUpdateGlobalUserRoleMutation();

        // act
        graphQlTester.document(mutation)
                .variable("name", "Non-Existent Role")
                .variable("input", input)
                .execute()
                .errors()
                .satisfy(errors -> assertThat(errors.getFirst().getMessage(),
                        containsString("Resource GlobalUserRole with id Non-Existent Role not found")));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.CHANGE_ROLES);
    }

    private UpdateGlobalUserRoleInput getSampleUpdateGlobalUserRoleInput() {
        return UpdateGlobalUserRoleInput.builder()
                .setGlobalPrivileges(List.of(GlobalPrivilege.LIST_USERS))
                .build();
    }

    private String getUpdateGlobalUserRoleMutation() {
        return gql("""
                mutation($name: String!, $input: UpdateGlobalUserRoleInput!) {
                    updateGlobalUserRole(name: $name, input: $input) {
                        name
                        globalPrivileges
                    }
                }
                """);
    }
}