package de.unistuttgart.iste.meitrex.scrumgame.api.role;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserRoleInput;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUserRole;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.GlobalPrivileges;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.GlobalUserRoleMatcher.matchingGlobalUserRoleInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@GraphQlApiTest
@ActiveProfiles("test")
class MutationCreateGlobalUserRoleTest {


    @Autowired
    private GlobalUserRoleRepository globalUserRoleRepository;

    @MockBean
    private AuthService authService;

    @Test
    void testCreateGlobalUserRole(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.CREATE_ROLE)).thenReturn(true);

        CreateGlobalUserRoleInput input = getSampleCreateGlobalUserRoleInput();
        String mutation = getCreateGlobalUserRoleMutation();

        // act
        GlobalUserRole result = graphQlTester.document(mutation)
                .variable("input", input)
                .execute()
                .path("createGlobalUserRole")
                .entity(GlobalUserRole.class)
                .get();

        // assert
        assertThat(result, matchingGlobalUserRoleInput(input));

        GlobalUserRoleEntity roleEntity = globalUserRoleRepository
                .findById(result.getName())
                .orElseThrow();
        assertThat(roleEntity, matchingGlobalUserRoleInput(input));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.CREATE_ROLE);
    }

    @Test
    void testCreateGlobalUserRoleWithoutPrivilege(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.CREATE_ROLE)).thenReturn(false);

        CreateGlobalUserRoleInput input = getSampleCreateGlobalUserRoleInput();
        String mutation = getCreateGlobalUserRoleMutation();

        // act & assert
        graphQlTester.document(mutation)
                .variable("input", input)
                .execute()
                .errors()
                .satisfy(errors ->
                        assertThat(errors.getFirst().getExtensions().get("exception"),
                                is("AuthorizationDeniedException")));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.CREATE_ROLE);
    }

    @Test
    void testCreateGlobalUserRoleAlreadyExists(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.CREATE_ROLE)).thenReturn(true);

        CreateGlobalUserRoleInput input = getSampleCreateGlobalUserRoleInput();
        String mutation = getCreateGlobalUserRoleMutation();

        globalUserRoleRepository.save(GlobalUserRoleEntity.builder()
                .name(input.getName())
                .build());

        // act & assert
        graphQlTester.document(mutation)
                .variable("input", input)
                .execute()
                .errors()
                .satisfy(errors ->
                        assertThat(errors.getFirst().getExtensions().get("exception"),
                                is("ResourceAlreadyExistsException")));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.CREATE_ROLE);
    }

    private CreateGlobalUserRoleInput getSampleCreateGlobalUserRoleInput() {
        return CreateGlobalUserRoleInput.builder()
                .setName("Test Role")
                .setGlobalPrivileges(List.of(GlobalPrivilege.CREATE_PROJECT,
                        GlobalPrivilege.READ_USER_PRIVATE_INFO))
                .build();
    }

    private String getCreateGlobalUserRoleMutation() {
        return gql("""
                mutation($input: CreateGlobalUserRoleInput!) {
                    createGlobalUserRole(input: $input) {
                        name
                        globalPrivileges
                    }
                }
                """);
    }
}
