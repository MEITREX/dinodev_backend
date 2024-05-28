package de.unistuttgart.iste.meitrex.scrumgame.matchers;

import de.unistuttgart.iste.meitrex.generated.dto.EventType;
import de.unistuttgart.iste.meitrex.generated.dto.FieldSchemaDefinition;
import de.unistuttgart.iste.meitrex.generated.dto.SchemaDefinition;
import org.hamcrest.Matcher;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.each;
import static org.hamcrest.Matchers.*;

public class EventTypeMatcher {
    private EventTypeMatcher() {
    }

    public static Matcher<EventType> matchingEventType(EventType eventType) {
        return allOf(
                hasProperty("name", is(eventType.getIdentifier())),
                hasProperty("description", is(eventType.getDescription())),
                hasProperty("messageTemplate", is(eventType.getMessageTemplate())),
                hasProperty("eventSchema", matchingSchema(eventType.getEventSchema())),
                hasProperty("defaultVisibility", is(eventType.getDefaultVisibility())),
                hasProperty("identifier", is(eventType.getIdentifier()))
        );
    }

    public static <T> Matcher<T> matchingSchema(SchemaDefinition schema) {
        return allOf(
                hasProperty("fields", containsInAnyOrder(
                        each(schema.getFields(), EventTypeMatcher::matchingFieldSchema)))
        );
    }

    public static <T> Matcher<T> matchingFieldSchema(FieldSchemaDefinition fieldSchema) {
        return allOf(
                hasProperty("name", is(fieldSchema.getName())),
                hasProperty("type", is(fieldSchema.getType())),
                hasProperty("required", is(fieldSchema.getRequired())),
                hasProperty("description", is(fieldSchema.getDescription())),
                hasProperty("allowedValues", containsInAnyOrder(fieldSchema.getAllowedValues().toArray()))
        );
    }
}
