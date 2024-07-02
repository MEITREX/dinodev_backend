package de.unistuttgart.iste.meitrex.scrumgame.matchers;

import de.unistuttgart.iste.meitrex.generated.dto.MeetingAttendee;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingAttendeeEmbeddable;
import org.hamcrest.Matcher;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.hasFeature;
import static org.hamcrest.Matchers.*;

public class MeetingAttendeeMatcher {

    private MeetingAttendeeMatcher() {
    }

    public static Matcher<MeetingAttendee> matchingMeetingAttendeeEntity(MeetingAttendeeEmbeddable attendeeEmbeddable) {
        return allOf(
                hasProperty("userId", is(attendeeEmbeddable.getUserId())),
                hasProperty("role", is(attendeeEmbeddable.getRole())),
                hasProperty("state", is(attendeeEmbeddable.getState()))
        );
    }

    public static Matcher<MeetingAttendee> matchingUUID(UUID uuid) {
        return hasFeature("userId", MeetingAttendee::getUserId, is(uuid));
    }
}
