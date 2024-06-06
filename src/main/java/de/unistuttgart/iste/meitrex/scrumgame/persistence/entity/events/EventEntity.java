package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.EventType;
import de.unistuttgart.iste.meitrex.generated.dto.EventVisibility;
import de.unistuttgart.iste.meitrex.rulesengine.DefaultEventTypes;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.*;

import static de.unistuttgart.iste.meitrex.scrumgame.util.PersistenceUtils.replaceContent;

@Slf4j
@Entity
@Table(name = "event")
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity implements Event, IWithId<UUID> {

    @Id
    @Column(nullable = false)
    @ToString.Include
    @Builder.Default
    @Setter
    private UUID id = UUID.randomUUID();

    @Column
    @ToString.Include
    @Setter
    private UUID projectId;

    @Column(columnDefinition = "TEXT")
    @ToString.Include
    @Setter
    private String message;

    @Column(nullable = false)
    @ToString.Include
    @Setter
    private OffsetDateTime timestamp;

    @Column
    @ToString.Include
    @NotNull
    @Setter
    private OffsetDateTime mostRecentChildTimestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @Nullable
    @Setter
    private UserDefinedEventTypeEntity dbEventType;

    @Column(nullable = true)
    @Nullable
    @ToString.Include
    @Setter
    private String eventTypeIdentifier;

    @Column(nullable = false)
    @NotNull
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @ToString.Include
    @Setter
    private EventVisibility visibility = EventVisibility.PUBLIC;

    @Column
    @ToString.Include
    @Setter
    private UUID userId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<UUID> visibleToUserIds = new ArrayList<>();

    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Builder.Default
    private List<EventEntity> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Setter
    @Nullable
    private EventEntity parent;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<TemplateFieldEmbeddable> eventData = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        if (parent != null) {
            parent.getChildren().remove(this);
        }
    }

    @PreUpdate
    @PrePersist
    private void updateChildTimestampOfParent() {
        if (parent != null) {
            parent.setMostRecentChildTimestamp(getTimestamp());
        }
    }

    @NotNull
    public String getEventTypeIdentifier() {
        return eventTypeIdentifier != null
               ? eventTypeIdentifier
               : DefaultEventTypes.UNKNOWN.getIdentifier();
    }

    @Override
    public EventType getEventType() {
        return Optional.<EventType>ofNullable(dbEventType)
                .orElse(DefaultEventTypes.UNKNOWN);
    }

    public void setEventType(EventType eventType) {
        eventTypeIdentifier = eventType.getIdentifier();
        if (eventType instanceof UserDefinedEventTypeEntity userDefinedEventTypeEntity) {
            dbEventType = userDefinedEventTypeEntity;
        } else {
            dbEventType = null;
        }
    }

    public void setVisibleToUserIds(List<UUID> visibleToUserIds) {
        this.visibleToUserIds = replaceContent(this.visibleToUserIds, visibleToUserIds);
    }

    public void setChildren(List<EventEntity> children) {
        this.children = replaceContent(this.children, children);
    }

    public void setEventData(List<TemplateFieldEmbeddable> eventData) {
        this.eventData = replaceContent(this.eventData, eventData);
    }

    public Optional<Event> getOptionalParent() {
        return Optional.ofNullable(parent);
    }

    public Optional<UUID> getOptionalProjectId() {
        return Optional.ofNullable(projectId);
    }

    public Optional<UUID> getOptionalUserId() {
        return Optional.ofNullable(userId);
    }
}
