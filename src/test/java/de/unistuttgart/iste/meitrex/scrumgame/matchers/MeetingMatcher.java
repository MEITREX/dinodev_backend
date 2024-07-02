package de.unistuttgart.iste.meitrex.scrumgame.matchers;

import de.unistuttgart.iste.meitrex.generated.dto.Meeting;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import org.hamcrest.Matcher;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.each;
import static org.hamcrest.Matchers.*;

public class MeetingMatcher {

    private MeetingMatcher() {
    }

    public static <T extends Meeting> Matcher<T> matchingMeetingEntity(MeetingEntity meetingEntity) {
        return allOf(
                hasProperty("projectId", is(meetingEntity.getProject().getId())),
                hasProperty("meetingType", is(meetingEntity.getMeetingType())),
                hasProperty("active", is(meetingEntity.isActive())),
                hasProperty("attendees", containsInAnyOrder(
                        each(meetingEntity.getAttendees(), MeetingAttendeeMatcher::matchingMeetingAttendeeEntity)))

        );
    }

}
