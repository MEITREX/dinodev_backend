package de.unistuttgart.iste.meitrex.scrumgame.service.sprint;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.CreateSprintInput;
import de.unistuttgart.iste.meitrex.generated.dto.Sprint;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint.SprintEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.SprintRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class SprintService extends AbstractCrudService<UUID, SprintEntity, Sprint> {

    private final Class<SprintEntity> entityClass = SprintEntity.class;
    private final Class<Sprint>       dtoClass    = Sprint.class;

    private final SprintRepository  repository;
    private final ProjectRepository projectRepository;
    private final ModelMapper       modelMapper;

    public Sprint createSprint(UUID projectId, CreateSprintInput input) {

        return create(() -> SprintEntity.builder()
                .project(projectRepository.findByIdOrThrow(projectId))
                .name(input.getName())
                .number(calculateNextSprintNumber(projectId))
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .build());
    }

    public List<Sprint> getSprints(UUID projectId) {
        List<SprintEntity> sprintEntities = repository.findAllByProjectId(projectId);

        return convertToDtos(sprintEntities);
    }

    public Optional<Sprint> findCurrentSprint(UUID projectId) {
        return findCurrentSprintEntity(projectId)
                .map(this::convertToDto);
    }

    public Sprint getSprint(UUID projectId, Integer sprintNumber) {
        return repository.findByProjectIdAndNumber(projectId, sprintNumber)
                .map(this::convertToDto)
                .orElseThrow(() -> new MeitrexNotFoundException("Sprint not found"));
    }

    private Optional<SprintEntity> findCurrentSprintEntity(UUID projectId) {
        return repository.findFirstByProjectIdOrderByNumberDesc(projectId);
    }

    private int calculateNextSprintNumber(UUID projectId) {
        return repository.countByProjectId(projectId) + 1;
    }

}
