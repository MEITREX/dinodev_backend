package de.unistuttgart.iste.meitrex.scrumgame.service.gamification;

import de.unistuttgart.iste.meitrex.common.exception.MeitrexNotFoundException;
import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.CreateEventTypeInput;
import de.unistuttgart.iste.meitrex.generated.dto.DefaultEventType;
import de.unistuttgart.iste.meitrex.generated.dto.EventType;
import de.unistuttgart.iste.meitrex.generated.dto.UpdateEventTypeInput;
import de.unistuttgart.iste.meitrex.rulesengine.EventTypeRegistry;
import de.unistuttgart.iste.meitrex.scrumgame.exception.PreDefinedModificationForbiddenException;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.UserDefinedEventTypeEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserDefinedEventTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
public class EventTypeService extends AbstractCrudService<String, UserDefinedEventTypeEntity, DefaultEventType> {

    private final EventTypeRegistry eventTypeRegistry;

    public EventTypeService(
            ModelMapper modelMapper,
            UserDefinedEventTypeRepository repository,
            EventTypeRegistry eventTypeRegistry
    ) {
        super(repository, modelMapper, UserDefinedEventTypeEntity.class, DefaultEventType.class);
        this.eventTypeRegistry = eventTypeRegistry;
    }

    /**
     * Creates a new event type.
     *
     * @param input the event type to create
     * @return the created game event type
     */
    public DefaultEventType createUserDefinedEventType(CreateEventTypeInput input) {
        return create(input);
    }

    /**
     * Returns all event types.
     *
     * @return all event types, including predefined ones
     */
    public List<EventType> getAllEventTypes() {
        Collection<EventType> predefinedEventTypes = eventTypeRegistry.getAll();
        List<DefaultEventType> userDefinedEventTypes = getAll();

        return Stream.concat(predefinedEventTypes.stream(), userDefinedEventTypes.stream()).toList();
    }

    /**
     * Returns an event type by its ID.
     *
     * @param identifier the identifier of the game event type
     * @return the game event type
     */
    public Optional<DefaultEventType> findEventType(String identifier) {
        return eventTypeRegistry.findById(identifier)
                .or(() -> find(identifier)) // find user defined event type
                .map(event -> {
                    if (event instanceof DefaultEventType defaultEventType) {
                        return defaultEventType;
                    }
                    return getModelMapper().map(event, DefaultEventType.class);
                });
    }

    /**
     * Updates an event type or creates it if it does not exist.
     *
     * @param id                   the identifier of the event type
     * @param updateEventTypeInput update dto
     * @return the updated game event type
     * @throws PreDefinedModificationForbiddenException if the game event type is predefined
     * @throws MeitrexNotFoundException                 if the game event type does not exist
     */
    public EventType updateUserDefinedEventType(String id, UpdateEventTypeInput updateEventTypeInput) {
        requireNotPredefined(id);
        return update(id, updateEventTypeInput);
    }

    /**
     * Deletes a game event type.
     *
     * @param id the identifier of the game event type
     * @throws PreDefinedModificationForbiddenException if the game event type is predefined
     */
    public boolean deleteUserDefinedEventType(String id) {
        requireNotPredefined(id);

        return delete(id);
    }

    @Override
    protected Class<UserDefinedEventTypeEntity> getEntityClass() {
        return UserDefinedEventTypeEntity.class;
    }

    @Override
    protected Class<DefaultEventType> getDtoClass() {
        return DefaultEventType.class;
    }

    private void requireNotPredefined(String id) {
        eventTypeRegistry.findById(id)
                .ifPresent(entity -> {
                    throw new PreDefinedModificationForbiddenException("EventType", id);
                });
    }

}
