package de.unistuttgart.iste.meitrex.scrumgame.mapping;

import de.unistuttgart.iste.meitrex.generated.dto.CreateProjectInput;
import de.unistuttgart.iste.meitrex.generated.dto.Project;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateProjectInput;
import de.unistuttgart.iste.meitrex.scrumgame.config.ModelMapperConfiguration;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjects;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static de.unistuttgart.iste.meitrex.scrumgame.matchers.ProjectMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;

class ProjectMappingTest {

    private final ModelMapper modelMapper = new ModelMapperConfiguration().modelMapper();

    @Test
    void testMapCreateInputToEntity() {
        // arrange
        CreateProjectInput createProjectInput = SampleProjects.getSampleCreateProjectInput();

        // act
        ProjectEntity projectEntity = modelMapper.map(createProjectInput, ProjectEntity.class);

        // assert
        assertThat(projectEntity, matchingProjectInput(createProjectInput));
    }

    @Test
    void testMapEntityToDto() {
        // arrange
        ProjectEntity projectEntity = SampleProjects.sampleProjectBuilder().build();

        // act
        Project project = modelMapper.map(projectEntity, Project.class);

        // assert
        assertThat(project, matchingProjectEntity(projectEntity));
    }

    @Test
    void testMapUpdateInputToEntity() {
        // arrange
        UpdateProjectInput updateProjectInput = SampleProjects.getSampleUpdateProjectInput();

        // act
        ProjectEntity projectEntity = modelMapper.map(updateProjectInput, ProjectEntity.class);

        // assert
        assertThat(projectEntity, matchingUpdateProjectInput(updateProjectInput));
    }


}
