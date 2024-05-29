package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.generated.dto.EventType;
import de.unistuttgart.iste.meitrex.generated.dto.EventVisibility;
import de.unistuttgart.iste.meitrex.rulesengine.DefaultEventTypes;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "event_type")
@Getter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDefinedEventTypeEntity implements EventType, IWithId<String> {

    @Id
    @ToString.Include
    @Setter
    private String identifier;

    @Column
    @ToString.Include
    @Setter
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Setter
    private SchemaEntity eventSchema;

    @Column
    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    @ToString.Include
    @Setter
    private EventVisibility defaultVisibility = EventVisibility.PUBLIC;

    @Column
    @Setter
    private String messageTemplate;

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @Builder.Default
    private List<EventEntity> events = new ArrayList<>();

    @PreRemove
    public void preRemove() {
        // Set the event type of all events to UNKNOWN to avoid foreign key constraint violations
        events.forEach(event -> event
                .setDbEventType(null)
                .setEventTypeIdentifier(DefaultEventTypes.UNKNOWN.getIdentifier()));
    }

    @Override
    public String getId() {
        return identifier;
    }
}