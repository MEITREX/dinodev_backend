package de.unistuttgart.iste.meitrex.scrumgame.data;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.FieldSchemaEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.SchemaEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.UserDefinedEventTypeEntity;

import java.util.List;

import static de.unistuttgart.iste.meitrex.generated.dto.AllowedDataType.INTEGER;
import static de.unistuttgart.iste.meitrex.generated.dto.AllowedDataType.STRING;

public class SampleEventTypes {

    private SampleEventTypes() {
    }

    public static UserDefinedEventTypeEntity.UserDefinedEventTypeEntityBuilder sampleUserDefinedEventTypeEntity() {
        return UserDefinedEventTypeEntity.builder()
                .identifier("Sample User Defined Event Type")
                .description("This is a sample user defined event type.")
                .messageTemplate("This is a sample message template.")
                .eventSchema(SchemaEntity.builder()
                        .fields(List.of(
                                FieldSchemaEntity.builder()
                                        .type(STRING)
                                        .required(true)
                                        .name("field1")
                                        .description("This is a sample field.")
                                        .build(),
                                FieldSchemaEntity.builder()
                                        .type(INTEGER)
                                        .required(false)
                                        .name("field2")
                                        .description("This is another sample field.")
                                        .build()))
                        .build());
    }
}
