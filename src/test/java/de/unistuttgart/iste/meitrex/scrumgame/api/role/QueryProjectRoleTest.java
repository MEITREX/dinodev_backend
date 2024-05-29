package de.unistuttgart.iste.meitrex.scrumgame.api.role;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectRole;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjects;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.ProjectRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjectRoles.sampleProjectRoleBuilder;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.ProjectRoleMatcher.matchingProjectRoleEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

@GraphQlApiTest
@ActiveProfiles("test")
class QueryProjectRoleTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectRoleRepository projectRoleRepository;

    @Test
    void testGetProjectRoles(GraphQlTester graphQlTester) {
        ProjectEntity project = projectRepository.save(SampleProjects.sampleProjectBuilder().build());
        ProjectEntity otherProject = projectRepository.save(SampleProjects.sampleProjectBuilder().build());

        ProjectRoleEntity role1 = projectRoleRepository.save(
                sampleProjectRoleBuilder(project, "Test Role 1").build());
        ProjectRoleEntity role2 = projectRoleRepository.save(
                sampleProjectRoleBuilder(project, "Test Role 2").build());

        // add a role for another project
        projectRoleRepository.save(
                sampleProjectRoleBuilder(otherProject, "Other Project Role").build());

        String query = getQueryAllProjectRoles();

        List<ProjectRole> result = graphQlTester.document(query)
                .variable("projectId", project.getId())
                .execute()
                .path("project.roles")
                .entityList(ProjectRole.class)
                .get();

        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder(
                matchingProjectRoleEntity(role1),
                matchingProjectRoleEntity(role2)
        ));
    }

    @Test
    void testGetProjectRole(GraphQlTester graphQlTester) {
        ProjectEntity project = projectRepository.save(SampleProjects.sampleProjectBuilder().build());

        ProjectRoleEntity role = projectRoleRepository.save(
                sampleProjectRoleBuilder(project, "Test Role").build());

        String query = getQueryForSingleProjectRole();

        ProjectRole result = graphQlTester.document(query)
                .variable("name", role.getName())
                .variable("projectId", project.getId())
                .execute()
                .path("project.role")
                .entity(ProjectRole.class)
                .get();

        assertThat(result, matchingProjectRoleEntity(role));
    }

    @Test
    void testGetProjectRoleNotFound(GraphQlTester graphQlTester) {
        ProjectEntity project = projectRepository.save(SampleProjects.sampleProjectBuilder().build());

        String query = getQueryForSingleProjectRole();

        graphQlTester.document(query)
                .variable("name", "Non-Existent Role")
                .variable("projectId", project.getId())
                .execute()
                .path("project.role")
                .valueIsNull();
    }

    private static String getQueryAllProjectRoles() {
        return gql("""
                query($projectId: UUID!) {
                    project(id: $projectId) {
                        roles {
                            projectId
                            name
                            gamifiedName
                            projectPrivileges
                        }
                    }
                }
                """);
    }

    private static String getQueryForSingleProjectRole() {
        return gql("""
                query($projectId: UUID!, $name: String!) {
                    project(id: $projectId) {
                        role(name: $name) {
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