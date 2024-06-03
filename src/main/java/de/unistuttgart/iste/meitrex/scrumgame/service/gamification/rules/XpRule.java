package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import de.unistuttgart.iste.meitrex.generated.dto.AllowedDataType;
import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.TemplateFieldInput;
import de.unistuttgart.iste.meitrex.rulesengine.Rule;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserStatsEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserStatsRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.event.ScrumGameEventTypes;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public abstract class XpRule implements Rule {

    private final UserStatsRepository                     userStatsRepository;
    private final EventPublisher<Event, CreateEventInput> eventPublisher;

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }

    @Override
    public boolean checkCondition(Event triggerEvent) {
        return true;
    }

    @Override
    public synchronized Optional<CreateEventInput> executeAction(Event triggerEvent) {
        UserStatsEntity userStats = getUserStatsEntity(triggerEvent.getUserId(), triggerEvent.getProjectId());

        int xpToAdd = getXp(triggerEvent);
        if (xpToAdd <= 0) {
            return Optional.empty();
        }

        int levelBefore = userStats.getLevel();
        userStats = addXp(userStats, xpToAdd);

        if (userStats.getLevel() > levelBefore) {
            publishLevelUpEvent(userStats.getLevel(), triggerEvent);
        }

        return Optional.of(getXpGainEvent(triggerEvent, xpToAdd));
    }

    private CreateEventInput getXpGainEvent(Event triggerEvent, int xpToAdd) {
        return CreateEventInput.builder()
                .setEventTypeIdentifier(ScrumGameEventTypes.XP_GAIN.getIdentifier())
                .setProjectId(triggerEvent.getProjectId())
                .setUserId(triggerEvent.getUserId())
                .setEventData(List.of(
                        new TemplateFieldInput("xp", AllowedDataType.INTEGER, Integer.toString(xpToAdd))))
                .setMessage(getXpMessage(triggerEvent, xpToAdd))
                .setParentId(triggerEvent.getId())
                .build();
    }

    public UserStatsEntity addXp(UserStatsEntity userStats, int xpToAdd) {
        return userStatsRepository.save(XpAdder.addXp(userStats, xpToAdd));
    }

    public void publishLevelUpEvent(int newLevel, Event triggerEvent) {
        CreateEventInput levelUpEvent = CreateEventInput.builder()
                .setEventTypeIdentifier(ScrumGameEventTypes.LEVEL_UP.getIdentifier())
                .setProjectId(triggerEvent.getProjectId())
                .setUserId(triggerEvent.getUserId())
                .setEventData(List.of(
                        new TemplateFieldInput("newLevel", AllowedDataType.INTEGER, Integer.toString(newLevel))))
                .build();

        eventPublisher.publishEvent(levelUpEvent);
    }

    public abstract int getXp(Event triggerEvent);

    public abstract String getXpMessage(Event triggerEvent, int xpToAdd);

    private UserStatsEntity getUserStatsEntity(UUID userId, UUID projectId) {
        UserProjectId userProjectId = new UserProjectId(userId, projectId);

        return userStatsRepository.findById(userProjectId)
                .orElseGet(() -> UserStatsEntity.builder().id(userProjectId).build());
    }

}
