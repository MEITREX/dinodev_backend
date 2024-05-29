package de.unistuttgart.iste.meitrex.scrumgame.service.sprint;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.CreateSprintInput;
import de.unistuttgart.iste.meitrex.generated.dto.Sprint;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint.SprintEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.SprintRepository;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SprintService extends AbstractCrudService<UUID, SprintEntity, Sprint> {

    private final SprintRepository  repository;
    private final ProjectRepository projectRepository;

    public SprintService(
            ModelMapper modelMapper,
            SprintRepository repository,
            ProjectRepository projectRepository
    ) {
        super(repository, modelMapper, SprintEntity.class, Sprint.class);
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    /**
     * Creates a new sprint for the specified project with the given input. Increments the current sprint number of the
     * project.
     *
     * @param projectId the ID of the project
     * @param input     the input for creating the sprint
     * @return the created sprint
     */
    public Sprint createNewSprint(UUID projectId, CreateSprintInput input) {
        ProjectEntity project = projectRepository.findByIdOrThrow(projectId);
        project.setCurrentSprintNumber(project.getCurrentSprintNumber() + 1);
        project = projectRepository.save(project);

        return createSprintWithNumber(project, project.getCurrentSprintNumber(), input);
    }

    /**
     * Creates a new sprint for the specified project with the given input and number. Does not increment the current
     * sprint number of the project.
     *
     * @param projectEntity the project entity to create the sprint for
     * @param number        the number of the sprint
     * @param input         the input for creating the sprint
     * @return the created sprint
     */
    public Sprint createSprintWithNumber(ProjectEntity projectEntity, int number, CreateSprintInput input) {
        return create(() -> SprintEntity.builder()
                .project(projectEntity)
                .name(input.getName())
                .number(number)
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .storyPointsPlanned(input.getStoryPointsPlanned())
                .customGoldChallengeReward(input.getCustomGoldChallengeReward())
                .animal(input.getAnimal())
                .build());
    }

    public List<Sprint> getSprints(UUID projectId) {
        List<SprintEntity> sprintEntities = repository.findAllByProjectIdOrderByNumber(projectId);

        return convertToDtos(sprintEntities);
    }

    public Optional<Sprint> findSprint(UUID projectId, @Nullable Integer sprintNumber) {
        if (sprintNumber == null) {
            return Optional.empty();
        }

        return repository.findByProjectIdAndNumber(projectId, sprintNumber)
                .map(this::convertToDto);
    }

    public Sprint getSprint(UUID projectId, @Nullable Integer sprintNumber) {
        return findSprint(projectId, sprintNumber)
                .orElseThrow(() -> new MeitrexNotFoundException("Sprint not found"));
    }
}
