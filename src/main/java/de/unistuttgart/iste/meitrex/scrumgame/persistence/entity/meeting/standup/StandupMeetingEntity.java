package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingAttendeeEmbeddable;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity
@Table(name = "standup_meeting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StandupMeetingEntity extends MeetingEntity {

    @Embedded
    private StandupMeetingSettingsEmbeddable standupMeetingSettings;

    @ElementCollection
    private List<MeetingAttendeeEmbeddable> order;

    @Embedded
    private MeetingAttendeeEmbeddable currentAttendee;

}
