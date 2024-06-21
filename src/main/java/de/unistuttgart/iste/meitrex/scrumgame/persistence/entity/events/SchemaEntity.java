package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events;

import de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils;
import de.unistuttgart.iste.meitrex.generated.dto.SchemaDefinition;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

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
    private List<FieldSchemaEntity> fields;

    public void setFields(List<FieldSchemaEntity> fields) {
        this.fields = MeitrexCollectionUtils.replaceContent(this.fields, fields);
    }
}
