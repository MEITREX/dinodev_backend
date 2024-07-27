package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.rulesengine.DefaultEventTypes;
import de.unistuttgart.iste.meitrex.rulesengine.Rule;
import de.unistuttgart.iste.meitrex.scrumgame.crs.CrsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserStatsEntity;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.DinoDevEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.UserStatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatCounterRule implements Rule {

    private final UserStatsService userStatsService;

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(DinoDevEventTypes.EVENT_REACTION.getIdentifier(),
                DefaultEventTypes.USER_MESSAGE.getIdentifier(),
                ImsEventTypes.COMMENT_ON_ISSUE.getIdentifier(),
                ImsEventTypes.ISSUE_COMPLETED.getIdentifier(),
                ImsEventTypes.ISSUE_CREATED.getIdentifier(),
                CrsEventTypes.OPEN_PULL_REQUEST.getIdentifier(),
                CrsEventTypes.CLOSE_PULL_REQUEST.getIdentifier(),
                CrsEventTypes.REVIEW_ACCEPT.getIdentifier(),
                CrsEventTypes.REVIEW_CHANGE_REQUEST.getIdentifier());
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        return triggerEvent.getUserId() != null && triggerEvent.getProjectId() != null;
    }

    @Override
    public Optional<CreateEventInput> executeAction(Event triggerEvent) {
        userStatsService.updateUserStats(triggerEvent.getUserId(), triggerEvent.getProjectId(),
                userStats -> incrementUserStatOfEvent(triggerEvent, userStats));

        // no follow-up event
        return Optional.empty();
    }

    private static void incrementUserStatOfEvent(Event triggerEvent, UserStatsEntity userStats) {
        if (triggerEvent.getEventType().getIdentifier().equals(DinoDevEventTypes.EVENT_REACTION.getIdentifier())) {
            userStats.setReactionsGiven(userStats.getReactionsGiven() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(ImsEventTypes.ISSUE_COMPLETED.getIdentifier())) {
            userStats.setIssuesCompleted(userStats.getIssuesCompleted() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(ImsEventTypes.ISSUE_CREATED.getIdentifier())) {
            userStats.setIssuesCreated(userStats.getIssuesCreated() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(CrsEventTypes.OPEN_PULL_REQUEST.getIdentifier())) {
            userStats.setPullRequestsCreated(userStats.getPullRequestsCreated() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(CrsEventTypes.CLOSE_PULL_REQUEST.getIdentifier())) {
            userStats.setPullRequestsClosed(userStats.getPullRequestsClosed() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(CrsEventTypes.REVIEW_ACCEPT.getIdentifier())
            || triggerEvent.getEventType().getIdentifier().equals(CrsEventTypes.REVIEW_CHANGE_REQUEST.getIdentifier())
        ) {
            userStats.setPullRequestsReviewed(userStats.getPullRequestsReviewed() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(ImsEventTypes.COMMENT_ON_ISSUE.getIdentifier())
            || triggerEvent.getEventType().getIdentifier().equals(DefaultEventTypes.USER_MESSAGE.getIdentifier())) {
            userStats.setCommentsWritten(userStats.getCommentsWritten() + 1);
        }
    }

}
