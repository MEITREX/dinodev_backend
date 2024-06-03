package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.rulesengine.Rule;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.AchievementService;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public abstract class AchievementRule implements Rule {

    protected final List<AchievementEntity> achievements;
    protected final AchievementService      achievementService;

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }

    @Override
    public Optional<CreateEventInput> executeAction(Event triggerEvent) {
        UserProjectId userProjectId = new UserProjectId(triggerEvent.getUserId(), triggerEvent.getProjectId());

        achievements.forEach(achievement ->
                achievementService.logAchievementProgress(userProjectId, achievement.getId(), 1));

        return Optional.empty();
    }
}
