package de.unistuttgart.iste.meitrex.scrumgame.api.role;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.causedBy;
import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.containsError;
import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleGlobalUserRoles.sampleGlobalUserRoleBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@GraphQlApiTest
@ActiveProfiles("test")
class MutationDeleteGlobalUserRoleTest {

    @Autowired
    private GlobalUserRoleRepository globalUserRoleRepository;
    @Autowired
    private GlobalUserRepository globalUserRepository;

    @MockBean
    private AuthService authService;

    @Test
    void testDeleteGlobalUserRole(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.DELETE_ROLE)).thenReturn(true);

        GlobalUserRoleEntity role = globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().build());
        GlobalUserEntity user = GlobalUserEntity.builder()
                .id(UUID.randomUUID())
                .username("test")
                .roles(List.of(role))
                .build();
        user = globalUserRepository.save(user);

        String mutation = getDeleteGlobalUserRoleMutation();

        // act
        boolean result = graphQlTester.document(mutation)
                .variable("name", role.getName())
                .execute()
                .path("deleteGlobalUserRole")
                .entity(Boolean.class)
                .get();

        // assert
        assertThat(result, is(true));
        assertThat(globalUserRoleRepository.existsById(role.getName()), is(false));
        assertThat(globalUserRepository.findByIdOrThrow(user.getId()).getRoles(), not(contains(role)));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivileges.DELETE_ROLE);
    }

    @Test
    void testDeleteGlobalUserRoleNotAuthorized(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.DELETE_ROLE)).thenReturn(false);

        GlobalUserRoleEntity role = globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().build());

        String mutation = getDeleteGlobalUserRoleMutation();

        // act & assert
        graphQlTester.document(mutation)
                .variable("name", role.getName())
                .execute()
                .errors()
                .satisfy(errors -> assertThat(errors, containsError(causedBy(AccessDeniedException.class))));
    }

    @Test
    void testDeleteGlobalUserRoleNotFound(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(GlobalPrivileges.DELETE_ROLE)).thenReturn(true);

        String mutation = getDeleteGlobalUserRoleMutation();

        // act
        boolean result = graphQlTester.document(mutation)
                .variable("name", "Non-Existent Role")
                .execute()
                .path("deleteGlobalUserRole")
                .entity(Boolean.class)
                .get();

        // assert
        assertThat(result, is(false));
    }

    private String getDeleteGlobalUserRoleMutation() {
        return gql("""
                mutation($name: String!) {
                    deleteGlobalUserRole(name: $name)
                }
                """);
    }
}
