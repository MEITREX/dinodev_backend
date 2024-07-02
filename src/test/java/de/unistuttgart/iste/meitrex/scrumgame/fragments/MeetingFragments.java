package de.unistuttgart.iste.meitrex.scrumgame.fragments;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;

public class MeetingFragments {

    private MeetingFragments() {
    }

    public static final String STANDUP_MEETING_FRAGMENT = gql("""
            fragment StandupMeetingFragment on StandupMeeting {
                projectId
                meetingType
                active
                attendees {
                    userId
                    role
                    state
                }
                currentAttendee {
                    userId
                    role
                    state
                }
                order {
                    userId
                    role
                    state
                }
                standupMeetingSettings {
                    countdownPerAttendee
                }
            }
            """);
}
