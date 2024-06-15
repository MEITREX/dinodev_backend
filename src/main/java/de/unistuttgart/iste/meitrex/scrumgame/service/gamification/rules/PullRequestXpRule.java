package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserStatsRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.vcs.VcsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.util.TemplateDataUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PullRequestXpRule extends XpAndLevelRule {

    public PullRequestXpRule(
            UserStatsRepository userStatsRepository,
            EventPublisher<Event, CreateEventInput> eventPublisher) {
        super(userStatsRepository, eventPublisher);
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(VcsEventTypes.OPEN_PULL_REQUEST.getIdentifier(),
                VcsEventTypes.REVIEW_ACCEPT.getIdentifier(),
                VcsEventTypes.REVIEW_CHANGE_REQUEST.getIdentifier());
    }

    @Override
    public int getXp(Event triggerEvent) {
        int commits = TemplateDataUtils.findIntField(triggerEvent, "commits").orElse(0);
        int additions = TemplateDataUtils.findIntField(triggerEvent, "additions").orElse(0);
        int deletions = TemplateDataUtils.findIntField(triggerEvent, "deletions").orElse(0);

        int xp = 100 + (commits - 1) * 5 + (int) Math.sqrt(10.0 * (additions + deletions));

        // half xp for reviews
        if (triggerEvent.getEventType().getIdentifier().equals(VcsEventTypes.REVIEW_ACCEPT.getIdentifier()) ||
            triggerEvent.getEventType().getIdentifier().equals(VcsEventTypes.REVIEW_CHANGE_REQUEST.getIdentifier())) {
            xp /= 2;
        }

        return xp;

    }

    @Override
    public String getXpMessage(Event triggerEvent, int xpToAdd) {
        Optional<Integer> commits = TemplateDataUtils.findIntField(triggerEvent, "commits");
        return "+" + xpToAdd + " XP for opening a pull request" +
               (commits.map(integer -> " with " + integer + " commits").orElse(""));
    }
}
