package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserStatsRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.DinoDevEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.util.TemplateDataUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MeetingXpRule extends XpAndLevelRule {

    private static final int STANDUP_BASE_XP       = 300;
    private static final int PLANNING_BASE_XP      = 700;
    private static final int RETRO_BASE_XP         = 500;
    private static final int MEETING_LEADER_FACTOR = 5;

    public MeetingXpRule(
            UserStatsRepository userStatsRepository,
            EventPublisher<Event, CreateEventInput> eventPublisher
    ) {
        super(userStatsRepository, eventPublisher);
    }

    @Override
    public List<String> getTriggerEventTypeIdentifiers() {
        return List.of(DinoDevEventTypes.SPRINT_PLANNING_ENDED.getIdentifier(),
                DinoDevEventTypes.STANDUP_ENDED.getIdentifier(),
                DinoDevEventTypes.RETROSPECTIVE_ENDED.getIdentifier());
    }

    @Override
    public int getXp(Event triggerEvent) {
        int baseXp = 0;
        if (DinoDevEventTypes.STANDUP_ENDED.getIdentifier().equals(triggerEvent.getEventType().getIdentifier())) {
            baseXp = STANDUP_BASE_XP;
        } else if (DinoDevEventTypes.SPRINT_PLANNING_ENDED.getIdentifier()
                .equals(triggerEvent.getEventType().getIdentifier())) {
            baseXp = PLANNING_BASE_XP;
        } else if (DinoDevEventTypes.RETROSPECTIVE_ENDED.getIdentifier()
                .equals(triggerEvent.getEventType().getIdentifier())) {
            baseXp = RETRO_BASE_XP;
        }
        return wasEventLeader(triggerEvent) ? baseXp * MEETING_LEADER_FACTOR : baseXp;
    }

    private boolean wasEventLeader(Event triggerEvent) {
        Optional<String> meetingLeaderId = TemplateDataUtils.findStringField(triggerEvent, "meetingLeaderId");
        return meetingLeaderId.isPresent() && meetingLeaderId.get().equals(triggerEvent.getUserId().toString());
    }

    @Override
    public String getXpMessage(Event triggerEvent, int xpToAdd) {
        return "+ " + xpToAdd + " XP for participating in a meeting"
               + (wasEventLeader(triggerEvent) ? " as a meeting leader" : "");
    }
}
