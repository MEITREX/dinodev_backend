package de.unistuttgart.iste.meitrex.scrumgame.service.event;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.EventEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.TemplateFieldEmbeddable;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.*;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class EventFactory {

    private final EventTypeService eventTypeService;
    private final ModelMapper      modelMapper;
    private final EventRepository  repository;

    public Event createDefaultEvent(CreateEventInput input) {
        return createDefaultEvent(createEventEntity(input));
    }

    public DefaultEvent createDefaultEvent(EventEntity eventEntity) {
        var result = modelMapper.map(eventEntity, DefaultEvent.class);
        result.setEventType(eventTypeService.findEventType(eventEntity.getEventTypeIdentifier()).orElseThrow());
        return result;
    }

    public EventEntity createEventEntity(CreateEventInput input) {
        EventEntity eventEntity = new EventEntity();

        EventType eventType = eventTypeService.findEventType(input.getEventTypeIdentifier()).orElseThrow();

        Optional<EventEntity> parentEvent = Optional.ofNullable(input.getParentId())
                .flatMap(repository::findById);

        eventEntity.setId(Optional.ofNullable(input.getId()).orElseGet(UUID::randomUUID));
        eventEntity.setProjectId(input.getProjectId());
        eventEntity.setMessage(
                Optional.ofNullable(input.getMessage())
                        .orElseGet(() -> fillMessageTemplate(eventType.getMessageTemplate(), input)));
        eventEntity.setTimestamp(Optional.ofNullable(input.getTimestamp()).orElseGet(OffsetDateTime::now));
        eventEntity.setMostRecentChildTimestamp(eventEntity.getTimestamp());
        eventEntity.setEventType(eventType);
        eventEntity.setVisibility(Optional.ofNullable(input.getVisibility()).orElse(eventType.getDefaultVisibility()));
        eventEntity.setUserId(input.getUserId());
        eventEntity.setParent(parentEvent.orElse(null));
        eventEntity.setEventData(input.getEventData()
                .stream()
                .map(field -> new TemplateFieldEmbeddable(field.getKey(), field.getType(), field.getValue()))
                .collect(Collectors.toList()));

        return eventEntity;
    }

    private static String fillMessageTemplate(String messageTemplate, CreateEventInput input) {
        Map<String, String> eventData = input.getEventData()
                .stream()
                .filter(field -> field.getKey() != null && field.getValue() != null)
                .collect(Collectors.toMap(TemplateFieldInput::getKey, TemplateFieldInput::getValue));

        StringSubstitutor substitutor = new StringSubstitutor(eventData)
                .setEscapeChar('\\')
                .setEnableSubstitutionInVariables(true)
                .setValueDelimiter(':')
                .setEnableUndefinedVariableException(false);
        return substitutor.replace(messageTemplate);
    }
}
