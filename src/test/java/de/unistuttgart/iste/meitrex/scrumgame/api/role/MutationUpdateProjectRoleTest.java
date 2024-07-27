package de.unistuttgart.iste.meitrex.scrumgame.api.role;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectRole;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateProjectRoleInput;
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
import org.springframework.test.context.ActiveProfiles;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjectRoles.sampleUpdateProjectRoleInput;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.ProjectRoleMatcher.matchingProjectRoleEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@GraphQlApiTest
@ActiveProfiles("test")
class MutationUpdateProjectRoleTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectRoleRepository projectRoleRepository;

    @MockBean
    private AuthService authService;

    @Test
    void testUpdateProjectRole(GraphQlTester graphQlTester) {
        ProjectEntity project = projectRepository.save(SampleProjects.sampleProjectBuilder().build());

        // arrange
        when(authService.hasPrivilege(ProjectPrivileges.UPDATE_ROLE, project.getId())).thenReturn(true);

        ProjectRoleEntity roleEntity = projectRoleRepository.save(
                SampleProjectRoles.sampleProjectRoleBuilder(project, "Role").build());

        UpdateProjectRoleInput input = sampleUpdateProjectRoleInput();
        String mutation = getUpdateProjectRoleMutation();

        // act
        ProjectRole result = graphQlTester.document(mutation)
                .variable("input", input)
                .variable("projectId", project.getId())
                .variable("name", roleEntity.getName())
                .execute()
                .path("mutateProject.updateRole")
                .entity(ProjectRole.class)
                .get();

        // assert
        ProjectRoleEntity updatedRoleEntity = projectRoleRepository
                .findById(new ProjectRoleId(roleEntity.getName(), project.getId()))
                .orElseThrow();
        assertThat(result, matchingProjectRoleEntity(updatedRoleEntity));

        // verify
        verify(authService, atLeastOnce()).hasPrivilege(ProjectPrivileges.UPDATE_ROLE, project.getId());
    }

    private String getUpdateProjectRoleMutation() {
        return gql("""
                mutation($projectId: UUID!, $name: String!, $input: UpdateProjectRoleInput!) {
                    mutateProject(id: $projectId) {
                        updateRole(name: $name, input: $input) {
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