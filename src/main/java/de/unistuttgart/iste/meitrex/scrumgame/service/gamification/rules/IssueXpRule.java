package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.Issue;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserStatsRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsService;
import org.springframework.stereotype.Component;

import java.util.*;

import static de.unistuttgart.iste.meitrex.scrumgame.util.TemplateDataUtils.findStringField;

@Component
public class IssueXpRule extends XpAndLevelRule {

    private final ImsService imsService;

    public IssueXpRule(
            UserStatsRepository userStatsRepository,
            EventPublisher<Event, CreateEventInput> eventPublisher,
            ImsService imsService
    ) {
        super(userStatsRepository, eventPublisher);
        this.imsService = imsService;
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(ImsEventTypes.ISSUE_COMPLETED.getIdentifier());
    }

    @Override
    public int getXp(Event triggerEvent) {
        Optional<Issue> issue = findStringField(triggerEvent, "issueId")
                .flatMap(issueId -> imsService.findIssue(triggerEvent.getProjectId(), issueId));

        if (issue.isEmpty()) {
            return 0;
        }

        Integer storyPoints = issue.get().getStoryPoints();
        if (storyPoints == null) {
            return 0;
        }

        int assigneeCount = issue.get().getAssigneeIds().size();
        int xp = 1000 * storyPoints;
        if (assigneeCount > 1) {
            return xp / assigneeCount;
        }

        return xp;
    }

    @Override
    public String getXpMessage(Event triggerEvent, int xpToAdd) {
        String issueTitle = findStringField(triggerEvent, "issueTitle").orElse("an issue");
        return "+ " + xpToAdd + " XP for completing " + issueTitle;
    }
}
