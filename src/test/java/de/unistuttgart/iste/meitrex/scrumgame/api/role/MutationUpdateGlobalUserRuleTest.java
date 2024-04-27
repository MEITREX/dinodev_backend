package de.unistuttgart.iste.meitrex.scrumgame.api.role;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.GlobalPrivileges;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.GlobalUserRoleMatcher.matchingGlobalUserRoleInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
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
        when(authService.hasPrivilege(GlobalPrivileges.UPDATE_ROLE)).thenReturn(true);

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
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.UPDATE_ROLE);
    }

    @Test
    void testUpdateGlobalUserRoleWithoutPrivilege(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.UPDATE_ROLE)).thenReturn(false);

        GlobalUserRoleEntity role = globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().build());
        UpdateGlobalUserRoleInput input = getSampleUpdateGlobalUserRoleInput();
        String mutation = getUpdateGlobalUserRoleMutation();

        // act
        graphQlTester.document(mutation)
                .variable("name", role.getName())
                .variable("input", input)
                .execute()
                .errors()
                .satisfy(errors -> assertThat(errors.getFirst().getExtensions().get("exception"),
                        is("AccessDeniedException")));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.UPDATE_ROLE);
    }

    @Test
    void testUpdateGlobalUserRoleNotFound(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.UPDATE_ROLE)).thenReturn(true);

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
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.UPDATE_ROLE);
    }

    private GlobalUserRoleEntity.GlobalUserRoleEntityBuilder sampleGlobalUserRoleBuilder() {
        return GlobalUserRoleEntity.builder()
                .name("Test Role")
                .globalPrivileges(List.of(GlobalPrivilege.CREATE_PROJECT, GlobalPrivilege.READ_USER_PRIVATE_INFO));
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