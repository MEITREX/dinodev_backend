package de.unistuttgart.iste.meitrex.scrumgame.api.user;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.GlobalUser;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateGlobalUserInput;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.causedBy;
import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.containsError;
import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.GlobalUserMatcher.matchingGlobalUserInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@GraphQlApiTest
@ActiveProfiles("test")
class MutationUpdateGlobalUserTest {

    @Autowired
    private GlobalUserRepository globalUserRepository;

    @MockBean
    private AuthService authService;

    @Test
    void testUpdateUserSameUser(GraphQlTester graphQlTester) {
        UUID userId = UUID.randomUUID();
        when(authService.getCurrentUserId()).thenReturn(userId);

        globalUserRepository.save(GlobalUserEntity.builder()
                .id(userId)
                .username("User")
                .avatar("Avatar")
                .build());

        UpdateGlobalUserInput input = getSampleUpdateGlobalUserInput();
        String mutation = getUpdateUserMutation();

        // act
        GlobalUser result = graphQlTester.document(mutation)
                .variable("input", input)
                .variable("id", userId)
                .execute()
                .path("updateGlobalUser")
                .entity(GlobalUser.class)
                .get();

        // assert
        assertThat(result, matchingGlobalUserInput(input));

        GlobalUserEntity userEntity = globalUserRepository.findById(result.getId()).orElseThrow();
        assertThat(userEntity, matchingGlobalUserInput(input));
    }

    @Test
    void testUpdateUserDifferentUser(GraphQlTester graphQlTester) {
        UUID userId = UUID.randomUUID();
        when(authService.getCurrentUserId()).thenReturn(userId);
        when(authService.hasPrivilege(GlobalPrivilege.UPDATE_USER)).thenReturn(true);

        UUID userIdToUpdate = UUID.randomUUID();
        globalUserRepository.save(GlobalUserEntity.builder()
                .id(userIdToUpdate)
                .username("User")
                .avatar("Avatar")
                .build());

        UpdateGlobalUserInput input = getSampleUpdateGlobalUserInput();
        String mutation = getUpdateUserMutation();

        // act
        GlobalUser result = graphQlTester.document(mutation)
                .variable("input", input)
                .variable("id", userIdToUpdate)
                .execute()
                .path("updateGlobalUser")
                .entity(GlobalUser.class)
                .get();

        // assert
        assertThat(result, matchingGlobalUserInput(input));

        GlobalUserEntity userEntity = globalUserRepository.findById(result.getId()).orElseThrow();
        assertThat(userEntity, matchingGlobalUserInput(input));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivilege.UPDATE_USER);
        verify(authService, atLeastOnce()).getCurrentUserId();
    }

    @Test
    void testUpdateUserNotVerified(GraphQlTester graphQlTester) {
        UUID userId = UUID.randomUUID();
        when(authService.getCurrentUserId()).thenReturn(userId);
        when(authService.hasPrivilege(GlobalPrivilege.UPDATE_USER)).thenReturn(false);

        UUID userIdToUpdate = UUID.randomUUID();
        globalUserRepository.save(GlobalUserEntity.builder()
                .id(userIdToUpdate)
                .username("User")
                .avatar("Avatar")
                .build());

        UpdateGlobalUserInput input = getSampleUpdateGlobalUserInput();
        String mutation = getUpdateUserMutation();

        // act & assert
        graphQlTester.document(mutation)
                .variable("input", input)
                .variable("id", userIdToUpdate)
                .execute()
                .errors()
                .satisfy(errors -> assertThat(errors, containsError(causedBy(AccessDeniedException.class))));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivilege.UPDATE_USER);
        verify(authService, atLeastOnce()).getCurrentUserId();
    }

    private UpdateGlobalUserInput getSampleUpdateGlobalUserInput() {
        return UpdateGlobalUserInput.builder()
                .setUsername("Updated User")
                .setAvatar("Updated Avatar")
                .build();
    }

    private String getUpdateUserMutation() {
        return gql("""
                mutation($id: UUID!, $input: UpdateGlobalUserInput!) {
                    updateGlobalUser(id: $id, input: $input) {
                        id
                        username
                        avatar
                    }
                }
                """);
    }
}
