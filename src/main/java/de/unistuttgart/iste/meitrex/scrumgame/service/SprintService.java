package de.unistuttgart.iste.meitrex.scrumgame.service;

import de.unistuttgart.iste.meitrex.generated.dto.CreateSprintInput;
import de.unistuttgart.iste.meitrex.generated.dto.Sprint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SprintService {
    public Sprint createSprint(UUID id, CreateSprintInput input, UUID userId) {
        return Sprint.builder().setId(UUID.randomUUID()).build();
    }
}
