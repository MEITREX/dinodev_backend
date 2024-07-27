package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.DinoDevEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.AchievementService;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.DefaultAchievements;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SocialButterflyAchievementRule extends AchievementRule {

    public SocialButterflyAchievementRule(AchievementService achievementService) {
        super(List.of(DefaultAchievements.ACHIEVEMENT_SOCIAL_BUTTERFLY), achievementService);
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(DinoDevEventTypes.EVENT_REACTION.getIdentifier());
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        return true;
    }

}
