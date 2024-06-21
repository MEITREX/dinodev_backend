package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils;
import de.unistuttgart.iste.meitrex.generated.dto.MeetingType;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Getter
@Entity
@Table(name = "meeting")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MeetingEntity implements IWithId<UUID> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<MeetingAttendeeEmbeddable> attendees = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Setter
    private MeetingType meetingType;

    @Column
    @Builder.Default
    @Setter
    private boolean active = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private ProjectEntity project;

    public void setAttendees(List<MeetingAttendeeEmbeddable> attendees) {
        this.attendees = MeitrexCollectionUtils.replaceContent(this.attendees, attendees);
    }
}
