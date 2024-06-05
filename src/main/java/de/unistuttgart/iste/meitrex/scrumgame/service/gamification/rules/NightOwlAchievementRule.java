package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.AchievementService;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.DefaultAchievements;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.*;

@Component
public class NightOwlAchievementRule extends AchievementRule {

    private static final int MIN_HOUR = 20;

    public NightOwlAchievementRule(AchievementService achievementService) {
        super(List.of(DefaultAchievements.ACHIEVEMENT_NIGHT_OWL), achievementService);
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(ImsEventTypes.ISSUE_COMPLETED.getIdentifier());
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        OffsetDateTime issueCompletedAt = triggerEvent.getTimestamp();

        return issueCompletedAt.getHour() >= MIN_HOUR;
    }

}
