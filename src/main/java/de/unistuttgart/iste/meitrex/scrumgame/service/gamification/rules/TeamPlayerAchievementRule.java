package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.AchievementService;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.DefaultAchievements;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TeamPlayerAchievementRule extends IssueProgressAchievementRule {

    public TeamPlayerAchievementRule(AchievementService achievementService) {
        super(List.of(DefaultAchievements.ACHIEVEMENT_TEAM_PLAYER), achievementService);
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        return super.checkCondition(triggerEvent) &&
               triggerEvent.getEventData().stream()
                       .filter(eventData -> eventData.getKey().equals("assigneeIds"))
                       .anyMatch(eventData -> eventData.getValue()
                               .contains(",")); // comma separated list of assigneeIds
    }
}
