package de.unistuttgart.iste.meitrex.scrumgame.data;

import de.unistuttgart.iste.meitrex.generated.dto.MeetingRole;
import de.unistuttgart.iste.meitrex.generated.dto.UserState;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingAttendeeEmbeddable;

import java.util.*;

public class SampleMeetingAttendees {

    private SampleMeetingAttendees() {
    }

    public static MeetingAttendeeEmbeddable.MeetingAttendeeEmbeddableBuilder sampleMeetingAttendeeEntity() {
        return MeetingAttendeeEmbeddable.builder()
                .setUserId(UUID.randomUUID())
                .setRole(MeetingRole.ATTENDEE)
                .setState(UserState.ONLINE);
    }
}
