package de.unistuttgart.iste.meitrex.scrumgame.api.user;

import de.unistuttgart.iste.meitrex.common.exception.ResourceAlreadyExistsException;
import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.BasicUserInfo;
import de.unistuttgart.iste.meitrex.generated.dto.CreateGlobalUserInput;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthConnector;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.causedBy;
import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.containsError;
import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.GlobalUserMatcher.matchingGlobalUserInput;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.GlobalUserRoleMatcher.matchingGlobalUserRoleEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@GraphQlApiTest
@ActiveProfiles("test")
public class MutationRegisterUserTest {

    @Autowired
    private GlobalUserRepository globalUserRepository;

    @MockBean
    private AuthService   authService;
    @MockBean
    private AuthConnector authConnector;

    @Test
    void testRegisterUser(GraphQlTester graphQlTester) {
        UUID userId = UUID.randomUUID();
        when(authService.getCurrentUserId()).thenReturn(userId);
        BasicUserInfo basicUserInfo = BasicUserInfo.builder().setId("test").setIsAdmin(false).build();
        when(authConnector.getUser()).thenReturn(Optional.of(basicUserInfo));

        CreateGlobalUserInput input = getSampleCreateGlobalUserInput();

        String mutation = getRegisterUserMutation();

        // act
        GlobalUser result = graphQlTester.document(mutation)
                .variable("input", input)
                .execute()
                .path("register")
                .entity(GlobalUser.class)
                .get();

        // assert
        assertThat(result, is(matchingGlobalUserInput(input)));
        assertThat(result.getRoles(), is(empty()));
        assertThat(result.getId(), is(userId));

        GlobalUserEntity userEntity = globalUserRepository.findById(result.getId()).orElseThrow();
        assertThat(userEntity, is(matchingGlobalUserInput(input)));
        assertThat(userEntity.getRoles(), is(empty()));
        assertThat(userEntity.getId(), is(userId));

        // verify
        verify(authService, atLeastOnce()).getCurrentUserId();
        verify(authConnector, atLeastOnce()).getUser();
    }

    @Test
    void testRegisterAdminUser(GraphQlTester graphQlTester) {
        UUID userId = UUID.randomUUID();
        when(authService.getCurrentUserId()).thenReturn(userId);
        BasicUserInfo basicUserInfo = BasicUserInfo.builder().setId("test").setIsAdmin(true).build();
        when(authConnector.getUser()).thenReturn(Optional.of(basicUserInfo));

        GlobalUserRoleEntity expectedRole = GlobalUserRoleEntity.builder()
                .name("ADMIN")
                .globalPrivileges(List.of(GlobalPrivilege.values()))
                .build();

        CreateGlobalUserInput input = getSampleCreateGlobalUserInput();

        String mutation = getRegisterUserMutation();

        // act
        GlobalUser result = graphQlTester.document(mutation)
                .variable("input", input)
                .execute()
                .path("register")
                .entity(GlobalUser.class)
                .get();

        // assert
        assertThat(result, is(matchingGlobalUserInput(input)));
        assertThat(result.getRoles(), hasItem(matchingGlobalUserRoleEntity(expectedRole)));
        assertThat(result.getId(), is(userId));

        GlobalUserEntity userEntity = globalUserRepository.findById(result.getId()).orElseThrow();
        assertThat(userEntity, is(matchingGlobalUserInput(input)));
        assertThat(userEntity.getRoles(), hasItem(matchingGlobalUserRoleEntity(expectedRole)));
        assertThat(userEntity.getId(), is(userId));

        // verify
        verify(authService, atLeastOnce()).getCurrentUserId();
        verify(authConnector, atLeastOnce()).getUser();
    }

    @Test
    void testRegisterUserAlreadyExists(GraphQlTester graphQlTester) {
        UUID userId = UUID.randomUUID();
        when(authService.getCurrentUserId()).thenReturn(userId);

        CreateGlobalUserInput input = getSampleCreateGlobalUserInput();
        String mutation = getRegisterUserMutation();

        globalUserRepository.save(GlobalUserEntity.builder()
                .id(userId)
                .username("other")
                .build());

        // act & assert
        graphQlTester.document(mutation)
                .variable("input", input)
                .execute()
                .errors()
                .satisfy(errors -> assertThat(errors, containsError(causedBy(ResourceAlreadyExistsException.class))));
    }

    private CreateGlobalUserInput getSampleCreateGlobalUserInput() {
        return CreateGlobalUserInput.builder()
                .setUsername("Test User")
                .setAvatar("Test Avatar")
                .build();
    }

    private String getRegisterUserMutation() {
        return gql("""
                mutation($input: CreateGlobalUserInput!) {
                    register(input: $input) {
                        id
                        username
                        avatar
                        roles {
                            name
                            globalPrivileges
                        }
                    }
                }
                """);
    }
}
