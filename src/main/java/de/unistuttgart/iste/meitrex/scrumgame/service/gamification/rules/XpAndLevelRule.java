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

import static de.unistuttgart.iste.meitrex.scrumgame.util.TemplateDataUtils.intField;

/**
 * A rule that adds XP to the user's stats and manages level ups.
 */
@RequiredArgsConstructor
public abstract class XpAndLevelRule implements Rule {

    private final UserStatsRepository userStatsRepository;
    private final EventPublisher<Event, CreateEventInput> eventPublisher;

    /**
     * Returns the amount of XP to add to the user's stats.
     *
     * @param triggerEvent The event that triggered the rule.
     * @return The amount of XP to add. If the return value is less than or equal to 0, no XP will be added.
     */
    protected abstract int getXp(Event triggerEvent);

    /**
     * Returns the message that should be displayed to the user when they gain XP.
     *
     * @param triggerEvent The event that triggered the rule.
     * @param xpToAdd      The amount of XP that was added.
     * @return The message to display to the user.
     */
    public abstract String getXpMessage(Event triggerEvent, int xpToAdd);

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
            doLevelUp(userStats, triggerEvent);
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

    public void doLevelUp(UserStatsEntity userStats, Event triggerEvent) {
        int newLevel = userStats.getLevel();
        int virtualCurrency = VirtualCurrencyCalculator.getVirtualCurrencyForLevelUp(newLevel);

        addVirtualCurrency(userStats, virtualCurrency);

        CreateEventInput levelUpEvent = getLevelUpEvent(triggerEvent, newLevel, virtualCurrency);

        eventPublisher.publishEvent(levelUpEvent);
    }

    private void addVirtualCurrency(UserStatsEntity userStats, int virtualCurrency) {
        userStats.setVirtualCurrency(userStats.getVirtualCurrency() + virtualCurrency);
        userStatsRepository.save(userStats);
    }

    private static CreateEventInput getLevelUpEvent(Event triggerEvent, int newLevel, int virtualCurrencyGained) {
        return CreateEventInput.builder()
                .setEventTypeIdentifier(ScrumGameEventTypes.LEVEL_UP.getIdentifier())
                .setProjectId(triggerEvent.getProjectId())
                .setUserId(triggerEvent.getUserId())
                .setEventData(List.of(
                        intField("newLevel", newLevel),
                        intField("virtualCurrency", virtualCurrencyGained)))
                .build();
    }


    private UserStatsEntity getUserStatsEntity(UUID userId, UUID projectId) {
        UserProjectId userProjectId = new UserProjectId(userId, projectId);

        return userStatsRepository.findById(userProjectId)
                .orElseGet(() -> UserStatsEntity.builder().id(userProjectId).build());
    }

}
