package de.unistuttgart.iste.meitrex.scrumgame.api.user;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleGlobalUserRoles.sampleGlobalUserRoleBuilder;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleGlobalUsers.sampleGlobalUserEntityBuilder;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.GlobalUserMatcher.matchingGlobalUserEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@GraphQlApiTest
@ActiveProfiles("test")
class QueryGlobalUserTest {

    @Autowired
    private GlobalUserRepository     globalUserRepository;
    @Autowired
    private GlobalUserRoleRepository globalUserRoleRepository;
    @MockBean
    private AuthService              authService;

    @Test
    void testGetGlobalUsers(GraphQlTester graphQlTester) {
        var rolesUser1 = List.of(sampleGlobalUserRoleBuilder().name("Role 1").build());
        var rolesUser2 = List.of(
                sampleGlobalUserRoleBuilder().name("Role 2").build(),
                sampleGlobalUserRoleBuilder().name("Role 3").build());
        globalUserRoleRepository.saveAll(rolesUser1);
        globalUserRoleRepository.saveAll(rolesUser2);

        GlobalUserEntity user1 = globalUserRepository.save(sampleGlobalUserEntityBuilder().roles(rolesUser1).build());
        GlobalUserEntity user2 = globalUserRepository.save(sampleGlobalUserEntityBuilder().roles(rolesUser2).build());

        String query = getAllGlobalUsersQuery();

        List<GlobalUser> result = graphQlTester.document(query)
                .execute()
                .path("globalUsers")
                .entityList(GlobalUser.class)
                .get();

        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder(
                matchingGlobalUserEntity(user1),
                matchingGlobalUserEntity(user2)
        ));
    }

    @Test
    void testGetGlobalUser(GraphQlTester graphQlTester) {
        GlobalUserEntity user = globalUserRepository.save(sampleGlobalUserEntityBuilder().build());

        String query = getGlobalUserQuery();

        GlobalUser result = graphQlTester.document(query)
                .variable("id", user.getId())
                .execute()
                .path("globalUser")
                .entity(GlobalUser.class)
                .get();

        assertThat(result, matchingGlobalUserEntity(user));
    }

    @Test
    void testGetGlobalUserNotFound(GraphQlTester graphQlTester) {
        String query = getGlobalUserQuery();

        graphQlTester.document(query)
                .variable("id", UUID.randomUUID())
                .execute()
                .path("globalUser")
                .valueIsNull();
    }

    @Test
    void testGetCurrentUser(GraphQlTester graphQlTester) {
        GlobalUserEntity user = globalUserRepository.save(sampleGlobalUserEntityBuilder().build());
        when(authService.getCurrentUserId()).thenReturn(user.getId());

        String query = getCurrentUserQuery();

        GlobalUser result = graphQlTester.document(query)
                .execute()
                .path("currentUser")
                .entity(GlobalUser.class)
                .get();

        assertThat(result, matchingGlobalUserEntity(user));
    }

    private String getCurrentUserQuery() {
        return gql("""
                query {
                    currentUser {
                        id
                        username
                        avatar
                        vcsUserId
                        roles {
                            name
                            globalPrivileges
                        }
                    }
                }
                """);
    }

    private String getGlobalUserQuery() {
        return gql("""
                query($id: UUID!) {
                    globalUser(id: $id) {
                        id
                        username
                        avatar
                        vcsUserId
                        roles {
                            name
                            globalPrivileges
                        }
                    }
                }
                """);
    }

    private String getAllGlobalUsersQuery() {
        return gql("""
                query {
                    globalUsers {
                        id
                        username
                        avatar
                        vcsUserId
                        roles {
                            name
                            globalPrivileges
                        }
                    }
                }
                """);
    }
}
