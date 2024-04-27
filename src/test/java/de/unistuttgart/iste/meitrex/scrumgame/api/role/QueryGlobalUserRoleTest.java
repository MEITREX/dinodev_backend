package de.unistuttgart.iste.meitrex.scrumgame.api.role;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUserRole;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.GlobalUserRoleMatcher.matchingGlobalUserRoleEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

@GraphQlApiTest
@ActiveProfiles("test")
class QueryGlobalUserRoleTest {

    @Autowired
    private GlobalUserRoleRepository globalUserRoleRepository;

    @Test
    void testGetGlobalUserRoles(GraphQlTester graphQlTester) {
        GlobalUserRoleEntity role1 = globalUserRoleRepository.save(
                sampleGlobalUserRoleBuilder().name("Test Role 1").build());
        GlobalUserRoleEntity role2 = globalUserRoleRepository.save(
                sampleGlobalUserRoleBuilder().name("Test Role 2").build());

        String query = getQueryAllUserRoles();

        List<GlobalUserRole> result = graphQlTester.document(query)
                .execute()
                .path("globalUserRoles")
                .entityList(GlobalUserRole.class)
                .get();

        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder(
                matchingGlobalUserRoleEntity(role1),
                matchingGlobalUserRoleEntity(role2)
        ));
    }

    @Test
    void testGetGlobalUserRole(GraphQlTester graphQlTester) {
        GlobalUserRoleEntity role = globalUserRoleRepository.save(sampleGlobalUserRoleBuilder().build());

        String query = getQueryForSingleUserRole();

        GlobalUserRole result = graphQlTester.document(query)
                .variable("name", role.getName())
                .execute()
                .path("globalUserRole")
                .entity(GlobalUserRole.class)
                .get();

        assertThat(result, matchingGlobalUserRoleEntity(role));
    }

    @Test
    void testGetGlobalUserRoleNotFound(GraphQlTester graphQlTester) {
        String query = getQueryForSingleUserRole();

        graphQlTester.document(query)
                .variable("name", "Non-Existent Role")
                .execute()
                .path("globalUserRole")
                .valueIsNull();
    }

    private GlobalUserRoleEntity.GlobalUserRoleEntityBuilder sampleGlobalUserRoleBuilder() {
        return GlobalUserRoleEntity.builder()
                .name("Test Role")
                .globalPrivileges(List.of(
                        GlobalPrivilege.CREATE_PROJECT,
                        GlobalPrivilege.READ_USER_PRIVATE_INFO));
    }

    private static @NotNull String getQueryAllUserRoles() {
        return gql("""
                query {
                    globalUserRoles {
                        name
                        globalPrivileges
                    }
                }
                """);
    }

    private static @NotNull String getQueryForSingleUserRole() {
        return gql("""
                query($name: String!) {
                    globalUserRole(name: $name) {
                        name
                        globalPrivileges
                    }
                }
                """);
    }

}