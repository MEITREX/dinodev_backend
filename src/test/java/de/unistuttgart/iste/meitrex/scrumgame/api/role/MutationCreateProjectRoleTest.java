package de.unistuttgart.iste.meitrex.scrumgame.api.role;

import de.unistuttgart.iste.meitrex.common.exception.ResourceAlreadyExistsException;
import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.CreateProjectRoleInput;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectRole;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjectRoles;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjects;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRoleRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.ProjectPrivileges;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.causedBy;
import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.containsError;
import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjectRoles.sampleCreateProjectRoleInput;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.ProjectRoleMatcher.matchingProjectRoleInput;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@GraphQlApiTest
@ActiveProfiles("test")
class MutationCreateProjectRoleTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectRoleRepository projectRoleRepository;

    @MockBean
    private AuthService authService;

    @Test
    void testCreateProjectRole(GraphQlTester graphQlTester) {
        ProjectEntity project = projectRepository.save(SampleProjects.sampleProjectBuilder().build());

        // arrange
        when(authService.hasPrivilege(ProjectPrivileges.CREATE_ROLE, project.getId())).thenReturn(true);

        CreateProjectRoleInput input = sampleCreateProjectRoleInput();
        String mutation = getCreateProjectRoleMutation();

        // act
        ProjectRole result = graphQlTester.document(mutation)
                .variable("input", input)
                .variable("projectId", project.getId())
                .execute()
                .path("mutateProject.createRole")
                .entity(ProjectRole.class)
                .get();

        // assert
        assertThat(result, matchingProjectRoleInput(input));

        ProjectRoleEntity roleEntity = projectRoleRepository
                .findById(new ProjectRoleId(input.getName(), project.getId()))
                .orElseThrow();
        assertThat(roleEntity, matchingProjectRoleInput(input));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(ProjectPrivileges.CREATE_ROLE, project.getId());
    }

    @Test
    void testCreateProjectRoleWithoutPrivilege(GraphQlTester graphQlTester) {
        // arrange
        when(authService.hasPrivilege(eq(ProjectPrivileges.CREATE_ROLE), any(UUID.class))).thenReturn(false);

        ProjectEntity project = projectRepository.save(SampleProjects.sampleProjectBuilder().build());

        CreateProjectRoleInput input = sampleCreateProjectRoleInput();
        String mutation = getCreateProjectRoleMutation();

        // act & assert
        graphQlTester.document(mutation)
                .variable("input", input)
                .variable("projectId", project.getId())
                .execute()
                .errors()
                .satisfy(errors ->
                        assertThat(errors, containsError(causedBy(AuthorizationDeniedException.class))));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(ProjectPrivileges.CREATE_ROLE, project.getId());
    }

    @Test
    void testCreateProjectRoleAlreadyExists(GraphQlTester graphQlTester) {
        // arrange
        ProjectEntity project = projectRepository.save(SampleProjects.sampleProjectBuilder().build());

        when(authService.hasPrivilege(eq(ProjectPrivileges.CREATE_ROLE), any(UUID.class))).thenReturn(true);

        CreateProjectRoleInput input = sampleCreateProjectRoleInput();
        String mutation = getCreateProjectRoleMutation();

        projectRoleRepository.save(SampleProjectRoles.sampleProjectRoleBuilder(project, input.getName()).build());

        // act & assert
        graphQlTester.document(mutation)
                .variable("input", input)
                .variable("projectId", project.getId())
                .execute()
                .errors()
                .satisfy(errors ->
                        assertThat(errors, containsError(causedBy(ResourceAlreadyExistsException.class))));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(ProjectPrivileges.CREATE_ROLE, project.getId());
    }

    private String getCreateProjectRoleMutation() {
        return gql("""
                mutation($projectId: UUID!, $input: CreateProjectRoleInput!) {
                    mutateProject(id: $projectId) {
                        createRole(input: $input) {
                            projectId
                            name
                            gamifiedName
                            projectPrivileges
                        }
                    }
                }
                """);
    }
}