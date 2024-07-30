package de.unistuttgart.iste.meitrex.scrumgame.service.sprint;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint.PlacedAssetEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint.SprintEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.SprintRepository;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.*;

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
     * Creates a new sprint for the specified project with the given input.
     * Sets the current sprint number of the project to the new sprint number.
     *
     * @param projectId the ID of the project
     * @param input     the input for creating the sprint
     * @return the created sprint
     */
    public Sprint createNewSprint(UUID projectId, CreateSprintInput input) {
        ProjectEntity project = projectRepository.findByIdOrThrow(projectId);

        int newSprintNumber = findMostRecentSprint(projectId)
                                      .map(Sprint::getNumber)
                                      .orElse(0) + 1;

        project.setCurrentSprintNumber(newSprintNumber);
        project = projectRepository.save(project);

        return createSprintWithNumber(project, newSprintNumber, input);
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
        return findSprintEntity(projectId, sprintNumber).map(this::convertToDto);
    }

    public Optional<SprintEntity> findSprintEntity(UUID projectId, @Nullable Integer sprintNumber) {
        if (sprintNumber == null) {
            return Optional.empty();
        }

        return repository.findByProjectIdAndNumber(projectId, sprintNumber);
    }

    public Optional<Sprint> findCurrentSprint(Project project) {
        return findSprint(project.getId(), project.getCurrentSprintNumber());
    }

    public Optional<Sprint> findPreviousSprint(Project project) {
        if (project.getCurrentSprintNumber() == null || project.getCurrentSprintNumber() <= 1) {
            return repository.findFirstByProjectIdOrderByNumberDesc(project.getId())
                    .map(this::convertToDto);
        }
        return findSprint(project.getId(), project.getCurrentSprintNumber() - 1);
    }

    public PlacedAsset placeAsset(Project project, PlaceAssetInput input, UUID placedBy) {
        SprintEntity sprintEntity = findSprintEntity(project.getId(), project.getCurrentSprintNumber())
                .orElseThrow(() -> new MeitrexNotFoundException("No current sprint found"));

        PlacedAssetEntity placedAssetEntity = getModelMapper().map(input, PlacedAssetEntity.class);
        placedAssetEntity.setPlacedByUserId(placedBy);

        sprintEntity.getPlacedAssets().add(placedAssetEntity);

        repository.save(sprintEntity);

        return getModelMapper().map(placedAssetEntity, PlacedAsset.class);
    }

    public Sprint getSprint(UUID projectId, @Nullable Integer sprintNumber) {
        return findSprint(projectId, sprintNumber)
                .orElseThrow(() -> new MeitrexNotFoundException("Sprint not found"));
    }

    public Sprint updateSprint(UUID id, int sprintNumber, UpdateSprintInput input) {
        SprintEntity sprintEntity = getSprintEntity(id, sprintNumber);

        getModelMapper().map(input, sprintEntity);

        return convertToDto(repository.save(sprintEntity));
    }

    private SprintEntity getSprintEntity(UUID id, int sprintNumber) {
        return findSprintEntity(id, sprintNumber)
                .orElseThrow(() -> new MeitrexNotFoundException("Sprint not found"));
    }

    public void updateSprintEntity(UUID projectId, Integer sprintNumber, Consumer<SprintEntity> update) {
        SprintEntity sprintEntity = getSprintEntity(projectId, sprintNumber);

        update.accept(sprintEntity);

        repository.save(sprintEntity);
    }

    private Optional<Sprint> findMostRecentSprint(UUID projectId) {
        return repository.findFirstByProjectIdOrderByNumberDesc(projectId)
                .map(this::convertToDto);
    }
}
