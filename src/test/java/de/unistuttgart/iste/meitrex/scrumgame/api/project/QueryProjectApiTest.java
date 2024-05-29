package de.unistuttgart.iste.meitrex.scrumgame.api.project;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjects.sampleProjectBuilder;
import static de.unistuttgart.iste.meitrex.scrumgame.fragments.ProjectFragments.BASE_PROJECT_FRAGMENT;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.ProjectMatcher.matchingProjectEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

@ActiveProfiles("test")
@GraphQlApiTest
class QueryProjectApiTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void testGetProjects(GraphQlTester graphQlTester) {
        ProjectEntity project1 = projectRepository.save(sampleProjectBuilder().build());
        ProjectEntity project2 = projectRepository.save(sampleProjectBuilder().build());

        String query = gql("""
                                   query {
                                       projects {
                                           ...ProjectFragment
                                       }
                                   }
                                   """ + BASE_PROJECT_FRAGMENT);

        List<Project> result = graphQlTester.document(query)
                .execute()
                .path("projects")
                .entityList(Project.class)
                .get();

        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder(
                matchingProjectEntity(project1),
                matchingProjectEntity(project2)
        ));
    }

    @Test
    void testGetProject(GraphQlTester graphQlTester) {
        ProjectEntity project = projectRepository.save(sampleProjectBuilder().build());

        String query = gql("""
                                   query($id: UUID!) {
                                       project(id: $id) {
                                           ...ProjectFragment
                                       }
                                   }
                                   """ + BASE_PROJECT_FRAGMENT);

        Project result = graphQlTester.document(query)
                .variable("id", project.getId())
                .execute()
                .path("project")
                .entity(Project.class)
                .get();

        assertThat(result, matchingProjectEntity(project));
    }

}
