package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events;

import de.unistuttgart.iste.meitrex.generated.dto.SchemaDefinition;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "schema")
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchemaEntity implements SchemaDefinition {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Setter
    private List<FieldSchemaEntity> fields;

}
