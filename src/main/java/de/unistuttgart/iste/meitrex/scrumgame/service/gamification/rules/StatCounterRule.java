package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.rulesengine.DefaultEventTypes;
import de.unistuttgart.iste.meitrex.rulesengine.Rule;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserStatsEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserStatsRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.ScrumGameEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.service.vcs.VcsEventTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatCounterRule implements Rule {

    // TODO remove need for id
    private static final UUID ID = UUID.fromString("3b539342-53db-4ce4-bcdc-8c4f7c040aa3");

    private final UserStatsRepository userStatsRepository;

    @Override
    public UUID getId() {
        return ID;
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(ScrumGameEventTypes.EVENT_REACTION.getIdentifier(),
                DefaultEventTypes.USER_MESSAGE.getIdentifier(),
                ImsEventTypes.COMMENT_ON_ISSUE.getIdentifier(),
                ImsEventTypes.ISSUE_COMPLETED.getIdentifier(),
                ImsEventTypes.ISSUE_CREATED.getIdentifier(),
                VcsEventTypes.OPEN_PULL_REQUEST.getIdentifier(),
                VcsEventTypes.CLOSE_PULL_REQUEST.getIdentifier(),
                VcsEventTypes.REVIEW_ACCEPT.getIdentifier(),
                VcsEventTypes.REVIEW_CHANGE_REQUEST.getIdentifier());
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        return triggerEvent.getUserId() != null && triggerEvent.getProjectId() != null;
    }

    @Override
    public synchronized Optional<CreateEventInput> executeAction(Event triggerEvent) {
        UserProjectId userProjectId = new UserProjectId(triggerEvent.getUserId(), triggerEvent.getProjectId());

        UserStatsEntity userStats = userStatsRepository.findById(userProjectId)
                .orElseGet(() -> UserStatsEntity.builder().id(userProjectId).build());

        if (triggerEvent.getEventType().getIdentifier().equals(ScrumGameEventTypes.EVENT_REACTION.getIdentifier())) {
            userStats.setReactionsGiven(userStats.getReactionsGiven() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(ImsEventTypes.ISSUE_COMPLETED.getIdentifier())) {
            userStats.setIssuesCompleted(userStats.getIssuesCompleted() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(ImsEventTypes.ISSUE_CREATED.getIdentifier())) {
            userStats.setIssuesCreated(userStats.getIssuesCreated() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(VcsEventTypes.OPEN_PULL_REQUEST.getIdentifier())) {
            userStats.setPullRequestsCreated(userStats.getPullRequestsCreated() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(VcsEventTypes.CLOSE_PULL_REQUEST.getIdentifier())) {
            userStats.setPullRequestsClosed(userStats.getPullRequestsClosed() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(VcsEventTypes.REVIEW_ACCEPT.getIdentifier())
            || triggerEvent.getEventType().getIdentifier().equals(VcsEventTypes.REVIEW_CHANGE_REQUEST.getIdentifier())
        ) {
            userStats.setPullRequestsReviewed(userStats.getPullRequestsReviewed() + 1);
        }

        if (triggerEvent.getEventType().getIdentifier().equals(ImsEventTypes.COMMENT_ON_ISSUE.getIdentifier())
            || triggerEvent.getEventType().getIdentifier().equals(DefaultEventTypes.USER_MESSAGE.getIdentifier())) {
            userStats.setCommentsWritten(userStats.getCommentsWritten() + 1);
        }

        userStatsRepository.save(userStats);


        // no follow-up event
        return Optional.empty();
    }

}
