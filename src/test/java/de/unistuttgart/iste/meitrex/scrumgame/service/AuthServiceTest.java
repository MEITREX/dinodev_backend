package de.unistuttgart.iste.meitrex.scrumgame.service;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserInProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AuthServiceTest {

    @Mock
    private GlobalUserRepository globalUserRepository;
    @Mock
    private UserInProjectRepository userInProjectRepository;

    @InjectMocks
    private AuthService authService;

    @AfterEach
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetCurrentUserId() {
        UUID expectedUserId = UUID.randomUUID();
        Jwt jwt = injectJwtMockWithUserId(expectedUserId);

        UUID actualUserId = authService.getCurrentUserId();

        assertThat(actualUserId, is(expectedUserId));

        verify(jwt, atLeastOnce()).getSubject();
    }

    @Test
    void testGetCurrentUserIdWithInvalidJwt() {
        Jwt jwt = mock(Jwt.class);
        when(jwt.getSubject()).thenReturn("not-a-uuid");
        injectJwtMock(jwt);

        assertThrows(AccessDeniedException.class, () -> authService.getCurrentUserId());

        verify(jwt, atLeastOnce()).getSubject();
    }

    @Test
    void testHasGlobalPrivilege() {
        UUID userId = UUID.randomUUID();
        injectJwtMockWithUserId(userId);

        when(globalUserRepository.findById(userId))
                .thenReturn(Optional.of(GlobalUserEntity.builder()
                        .id(userId)
                        .roles(List.of(GlobalUserRoleEntity.builder()
                                        .globalPrivileges(List.of(GlobalPrivilege.UPDATE_USER))
                                        .build(),
                                GlobalUserRoleEntity.builder()
                                        .globalPrivileges(List.of(GlobalPrivilege.LIST_USERS, GlobalPrivilege.CREATE_PROJECT))
                                        .build()))
                        .build()));

        assertThat(authService.hasPrivilege(GlobalPrivilege.UPDATE_USER), is(true));
        assertThat(authService.hasPrivilege(GlobalPrivilege.LIST_USERS), is(true));
        assertThat(authService.hasPrivilege(GlobalPrivilege.CREATE_PROJECT), is(true));

        assertThat(authService.hasPrivilege(GlobalPrivilege.DELETE_USER), is(false));

        verify(globalUserRepository, atLeastOnce()).findById(userId);
    }

    @Test
    void testHasGlobalPrivilegeWithNoUser() {
        UUID userId = UUID.randomUUID();
        injectJwtMockWithUserId(userId);

        when(globalUserRepository.findById(userId))
                .thenReturn(Optional.empty());

        boolean hasPrivilege = authService.hasPrivilege(GlobalPrivilege.LIST_USERS);

        assertThat(hasPrivilege, is(false));

        verify(globalUserRepository).findById(userId);
    }

    @Test
    void testHasProjectPrivilege() {
        UUID userId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();
        injectJwtMockWithUserId(userId);

        when(userInProjectRepository.findById(new UserProjectId(userId, projectId)))
                .thenReturn(Optional.of(UserInProjectEntity.builder()
                        .id(new UserProjectId(userId, projectId))
                        .roles(List.of(ProjectRoleEntity.builder()
                                        .projectPrivileges(List.of(ProjectPrivilege.UPDATE_PROJECT))
                                        .build(),
                                ProjectRoleEntity.builder()
                                        .projectPrivileges(List.of(ProjectPrivilege.CREATE_SPRINT, ProjectPrivilege.UPDATE_SPRINT))
                                        .build()))
                        .build()));

        assertThat(authService.hasPrivilege(ProjectPrivilege.UPDATE_PROJECT, projectId), is(true));
        assertThat(authService.hasPrivilege(ProjectPrivilege.CREATE_SPRINT, projectId), is(true));
        assertThat(authService.hasPrivilege(ProjectPrivilege.UPDATE_SPRINT, projectId), is(true));

        assertThat(authService.hasPrivilege(ProjectPrivilege.DELETE_PROJECT, projectId), is(false));

        verify(userInProjectRepository, atLeastOnce()).findById(new UserProjectId(userId, projectId));
    }

    private Jwt injectJwtMockWithUserId(UUID userId) {
        Jwt jwt = mock(Jwt.class);
        when(jwt.getSubject()).thenReturn(userId.toString());
        injectJwtMock(jwt);
        return jwt;
    }

    private void injectJwtMock(Jwt jwt) {
        Authentication authentication = new JwtAuthenticationToken(jwt);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}
