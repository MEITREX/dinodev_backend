package de.unistuttgart.iste.meitrex.scrumgame.api.project;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.generated.dto.ProjectPrivilege.UPDATE_PROJECT;
import static de.unistuttgart.iste.meitrex.scrumgame.fragments.ProjectFragments.BASE_PROJECT_FRAGMENT;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.ProjectMatcher.matchingUpdateProjectInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@GraphQlApiTest
@ActiveProfiles("test")
class MutationUpdateProjectTest {

    @Autowired
    private ProjectRepository projectRepository;
    @MockBean
    private AuthService authService;

    @Test
    @Transactional
    @Commit
    void testUpdateProject(GraphQlTester graphQlTester) {
        // arrange
        UUID userId = UUID.randomUUID();
        ProjectEntity project = createProject();
        when(authService.hasPrivilege(UPDATE_PROJECT, project.getId())).thenReturn(true);
        when(authService.getCurrentUserId()).thenReturn(userId);

        UpdateProjectInput input = getSampleUpdateProjectInput();
        String mutation = getUpdateProjectMutation();

        // act
        Project result = graphQlTester.document(mutation)
                .variable("projectId", project.getId())
                .variable("input", input)
                .execute()
                .path("updateProject")
                .entity(Project.class)
                .get();

        // assert
        assertThat(result, matchingUpdateProjectInput(input));

        ProjectEntity projectEntity = projectRepository.findById(result.getId()).orElseThrow();
        assertThat(projectEntity, matchingUpdateProjectInput(input));

        verify(authService, atLeastOnce()).hasPrivilege(UPDATE_PROJECT, project.getId());
    }

    private ProjectEntity createProject() {
        return projectRepository.save(ProjectEntity.builder().id(UUID.randomUUID()).build());
    }

    private static UpdateProjectInput getSampleUpdateProjectInput() {
        return UpdateProjectInput.builder()
                .setName("Updated Test Project")
                .setDescription("Updated Test Project")
                .setProjectSettings(ProjectSettingsInput.builder()
                        .setCodeRepositorySettings(CodeRepositorySettingsInput.builder()
                                .setCodeRepositoryName("Updated Test IMS")
                                .build())
                        .setImsSettings(ImsSettingsInput.builder()
                                .setImsName("Updated Test IMS")
                                .build())
                        .build())
                .build();
    }

    private static @NotNull String getUpdateProjectMutation() {
        return gql("""
                           mutation($projectId: UUID!, $input: UpdateProjectInput!) {
                               updateProject(id: $projectId, input: $input) {
                                   ...ProjectFragment
                               }
                           }""" + BASE_PROJECT_FRAGMENT);
    }
}
