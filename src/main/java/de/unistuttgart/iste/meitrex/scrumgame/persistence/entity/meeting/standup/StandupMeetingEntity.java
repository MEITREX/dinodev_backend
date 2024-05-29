package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingAttendeeEmbeddable;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
