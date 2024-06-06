package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events;

import de.unistuttgart.iste.meitrex.generated.dto.AllowedDataType;
import de.unistuttgart.iste.meitrex.generated.dto.FieldSchemaDefinition;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

@Entity
@Table(name = "field_schema")
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldSchemaEntity implements FieldSchemaDefinition {

    @Id
    @GeneratedValue
    private UUID            id;
    private String          name;
    private AllowedDataType type;
    private String          description;
    private boolean         required;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> allowedValues = List.of();

    @Override
    public boolean getRequired() {
        return required;
    }

}
