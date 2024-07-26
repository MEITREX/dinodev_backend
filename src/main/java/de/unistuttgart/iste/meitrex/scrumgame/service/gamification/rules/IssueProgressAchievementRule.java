package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.DataField;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.AchievementService;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.DefaultAchievements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IssueProgressAchievementRule extends AchievementRule {

    @Autowired
    public IssueProgressAchievementRule(AchievementService achievementService) {
        super(List.of(
                DefaultAchievements.ACHIEVEMENT_FIRST_STEPS,
                DefaultAchievements.ACHIEVEMENT_GETTING_STARTED,
                DefaultAchievements.ACHIEVEMENT_TASK_CHAMPION,
                DefaultAchievements.ACHIEVEMENT_TASK_LEGEND
        ), achievementService);
    }

    protected IssueProgressAchievementRule(List<AchievementEntity> achievements,
            AchievementService achievementService) {
        super(achievements, achievementService);
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(ImsEventTypes.ISSUE_COMPLETED.getIdentifier());
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        return triggerEvent.getEventData().stream()
                .filter(eventData -> "assigneeIds".equals(eventData.getKey()))
                .anyMatch(eventData -> eventData.getValue().contains(triggerEvent.getUserId().toString()));
    }

    @Override
    public Optional<CreateEventInput> executeAction(Event triggerEvent) {
        var assigneeIds = triggerEvent.getEventData().stream()
                .filter(eventData -> "assigneeIds".equals(eventData.getKey()))
                .map(DataField::getValue)
                .findFirst()
                .map(assigneeIdString -> assigneeIdString.split(","))
                .map(Arrays::asList)
                .stream()
                .flatMap(Collection::stream)
                .map(UUID::fromString)
                .toList();

        if (assigneeIds.isEmpty()) {
            assigneeIds = List.of(triggerEvent.getUserId());
        }

        for (UUID assigneeId : assigneeIds) {
            UserProjectId userProjectId = new UserProjectId(assigneeId, triggerEvent.getProjectId());

            achievements.forEach(achievement ->
                    achievementService.logAchievementProgress(userProjectId, achievement.getId(), 1));
        }

        return Optional.empty();
    }

}
