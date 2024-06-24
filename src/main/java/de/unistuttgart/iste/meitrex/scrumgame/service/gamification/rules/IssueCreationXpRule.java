package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserStatsRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IssueCreationXpRule extends XpAndLevelRule {

    private static final int XP_FOR_ISSUE_CREATION = 200;

    public IssueCreationXpRule(
            UserStatsRepository userStatsRepository,
            EventPublisher<Event, CreateEventInput> eventPublisher
    ) {
        super(userStatsRepository, eventPublisher);
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(ImsEventTypes.ISSUE_CREATED.getIdentifier());
    }

    @Override
    public int getXp(Event triggerEvent) {
        return XP_FOR_ISSUE_CREATION;
    }

    @Override
    public String getXpMessage(Event triggerEvent, int xpToAdd) {
        return "+ " + xpToAdd + " XP for creating an issue.";
    }
}
