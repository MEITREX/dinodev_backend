package de.unistuttgart.iste.meitrex.scrumgame.matchers;

import de.unistuttgart.iste.meitrex.generated.dto.StandupMeeting;
import de.unistuttgart.iste.meitrex.generated.dto.StandupMeetingSettings;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup.StandupMeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup.StandupMeetingSettingsEmbeddable;
import org.hamcrest.Matcher;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.each;
import static org.hamcrest.Matchers.*;

public class StandupMeetingMatcher {

    private StandupMeetingMatcher() {
    }

    public static Matcher<StandupMeeting> matchingStandupMeetingEntity(StandupMeetingEntity standupMeetingEntity) {

        return allOf(
                MeetingMatcher.matchingMeetingEntity(standupMeetingEntity),
                hasProperty("standupMeetingSettings",
                        is(matchingStandupMeetingSettings(standupMeetingEntity.getStandupMeetingSettings()))),
                // order in StandupMeetingEntity is a list of UUIDs, while in StandupMeeting it is a list of MeetingAttendee
                // so we have to adjust the matcher
                hasProperty("order", containsInAnyOrder(
                        each(standupMeetingEntity.getOrder(), MeetingAttendeeMatcher::matchingUUID))),
                standupMeetingEntity.getCurrentAttendee() == null
                ? hasProperty("currentAttendee", nullValue())
                : hasProperty("currentAttendee",
                        is(MeetingAttendeeMatcher.matchingUUID(standupMeetingEntity.getCurrentAttendee())))
        );
    }

    private static Matcher<StandupMeetingSettings> matchingStandupMeetingSettings(StandupMeetingSettingsEmbeddable standupMeetingSettings) {
        return hasProperty("countdownPerAttendee", is(standupMeetingSettings.getCountdownPerAttendee()));
    }

}
