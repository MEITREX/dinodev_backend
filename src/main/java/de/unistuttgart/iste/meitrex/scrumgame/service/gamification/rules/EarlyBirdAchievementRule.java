package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.AchievementService;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.DefaultAchievements;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.*;

@Component
public class EarlyBirdAchievementRule extends AchievementRule {

    public EarlyBirdAchievementRule(AchievementService achievementService) {
        super(List.of(DefaultAchievements.ACHIEVEMENT_EARLY_BIRD), achievementService);
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(ImsEventTypes.ISSUE_COMPLETED.getIdentifier());
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        OffsetDateTime issueCompletedAt = triggerEvent.getTimestamp();

        return issueCompletedAt.getHour() <= 9;
    }

}
