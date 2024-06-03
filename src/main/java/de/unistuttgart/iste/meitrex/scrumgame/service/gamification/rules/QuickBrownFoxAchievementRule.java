package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.Sprint;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.AchievementService;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.DefaultAchievements;
import de.unistuttgart.iste.meitrex.scrumgame.service.project.ProjectService;
import de.unistuttgart.iste.meitrex.scrumgame.service.sprint.SprintService;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.*;

@Component
public class QuickBrownFoxAchievementRule extends AchievementRule {

    private final SprintService  sprintService;
    private final ProjectService projectService;

    public QuickBrownFoxAchievementRule(
            AchievementService achievementService,
            SprintService sprintService,
            ProjectService projectService
    ) {
        super(List.of(DefaultAchievements.ACHIEVEMENT_QUICK_BROWN_FOX), achievementService);
        this.sprintService = sprintService;
        this.projectService = projectService;
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(ImsEventTypes.ISSUE_COMPLETED.getIdentifier());
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        Integer currentSprintNumber = projectService
                .getProjectOrThrow(triggerEvent.getProjectId())
                .getCurrentSprintNumber();

        Optional<Sprint> sprint = sprintService.findSprint(triggerEvent.getProjectId(), currentSprintNumber);

        if (sprint.isEmpty()) {
            return false;
        }

        OffsetDateTime issueCompletedAt = triggerEvent.getTimestamp();
        OffsetDateTime sprintStart = sprint.get().getStartDate();

        return issueCompletedAt.toLocalDate().isBefore(sprintStart.toLocalDate().plusDays(3));
    }

}
