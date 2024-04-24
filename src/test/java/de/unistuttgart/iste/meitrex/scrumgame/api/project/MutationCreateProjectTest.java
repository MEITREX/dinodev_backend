package de.unistuttgart.iste.meitrex.scrumgame.api.project;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.fragments.ProjectFragments.BASE_PROJECT_FRAGMENT;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.ProjectMatcher.matchingProjectInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@GraphQlApiTest
class MutationCreateProjectTest {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserInProjectRepository userInProjectRepository;
    @Autowired
    private GlobalUserRepository globalUserRepository;
    @MockBean
    private AuthService authService;

    @Test
    @Transactional
    @Commit
    void testCreateProject(GraphQlTester graphQlTester) {
        // arrange
        GlobalUserEntity user = createUser();
        when(authService.hasPrivilege(GlobalPrivilege.CREATE_PROJECT)).thenReturn(true);
        when(authService.getCurrentUserId()).thenReturn(user.getId());

        CreateProjectInput input = getSampleCreateProjectInput();
        String mutation = getCreateProjectMutation();

        // act
        Project result = graphQlTester.document(mutation)
                .variable("input", input)
                .execute()
                .path("createProject")
                .entity(Project.class)
                .get();

        // assert
        assertThat(result, matchingProjectInput(input));

        ProjectEntity projectEntity = projectRepository.findById(result.getId()).orElseThrow();
        assertThat(projectEntity, matchingProjectInput(input));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivilege.CREATE_PROJECT);
        verify(authService, atLeastOnce()).getCurrentUserId();
    }

    @Test
    void testCreateProjectNotAuthorized(GraphQlTester graphQlTester) {
        // arrange
        GlobalUserEntity user = createUser();
        when(authService.hasPrivilege(GlobalPrivilege.CREATE_PROJECT)).thenReturn(false);
        when(authService.getCurrentUserId()).thenReturn(user.getId());

        String mutation = getCreateProjectMutation();
        CreateProjectInput input = getSampleCreateProjectInput();

        // act & assert
        graphQlTester.document(mutation)
                .variable("input", input)
                .execute()
                .errors()
                .satisfy(errors ->
                        assertThat(
                                errors.getFirst().getExtensions().get("exception"),
                                is("AccessDeniedException")));
    }

    @Test
    @Transactional
    @Commit
    void testCreateProjectCorrectInitialized(GraphQlTester graphQlTester) {
        // arrange
        GlobalUserEntity user = createUser();
        when(authService.hasPrivilege(GlobalPrivilege.CREATE_PROJECT)).thenReturn(true);
        when(authService.getCurrentUserId()).thenReturn(user.getId());

        String mutation = getCreateProjectMutation();
        CreateProjectInput input = getSampleCreateProjectInput();

        // act
        graphQlTester.document(mutation).variable("input", input).executeAndVerify();

        // assert
        ProjectEntity projectEntity = projectRepository.findAll().getFirst();
        UserInProjectEntity userInProjectEntity
                = userInProjectRepository.findByIdOrThrow(new UserProjectId(user.getId(), projectEntity.getId()));

        // check that user has admin role in his created project
        assertThat(userInProjectEntity.getRoles(), hasSize(1));
        assertThat(userInProjectEntity.getRoles().getFirst().getProjectPrivileges(),
                containsInAnyOrder(ProjectPrivilege.values()));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(GlobalPrivilege.CREATE_PROJECT);
        verify(authService, atLeastOnce()).getCurrentUserId();
    }

    private GlobalUserEntity createUser() {
        return globalUserRepository.save(GlobalUserEntity.builder().id(UUID.randomUUID()).build());
    }

    private static CreateProjectInput getSampleCreateProjectInput() {
        return CreateProjectInput.builder()
                .setName("Test Project")
                .setDescription("Test Description")
                .setProjectSettings(ProjectSettingsInput.builder()
                        .setCodeRepositorySettings(CodeRepositorySettingsInput.builder()
                                .setCodeRepositoryName("Test Repository")
                                .build())
                        .setImsSettings(ImsSettingsInput.builder()
                                .setImsName("Test IMS")
                                .build())
                        .build())
                .build();
    }

    private String getCreateProjectMutation() {
        return gql("""
                           mutation($input: CreateProjectInput!) {
                               createProject(input: $input) {
                                   ...ProjectFragment
                               }
                           }""" + BASE_PROJECT_FRAGMENT);
    }
}
