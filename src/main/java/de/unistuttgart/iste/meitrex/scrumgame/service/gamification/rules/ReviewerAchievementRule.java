package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.scrumgame.crs.CrsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.AchievementService;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.DefaultAchievements;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ReviewerAchievementRule extends AchievementRule {

    public ReviewerAchievementRule(AchievementService achievementService) {
        super(List.of(DefaultAchievements.ACHIEVEMENT_REVIEWER), achievementService);
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(CrsEventTypes.REVIEW_ACCEPT.getIdentifier(),
                CrsEventTypes.REVIEW_CHANGE_REQUEST.getIdentifier());
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        return true;
    }

}
