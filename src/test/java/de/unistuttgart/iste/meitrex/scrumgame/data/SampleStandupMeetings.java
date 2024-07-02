package de.unistuttgart.iste.meitrex.scrumgame.data;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup.StandupMeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup.StandupMeetingSettingsEmbeddable;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;

import java.util.*;

public class SampleStandupMeetings {

    private SampleStandupMeetings() {

    }

    public static StandupMeetingEntity.StandupMeetingEntityBuilder<?, ?> sampleStandupMeetingEntity(
            ProjectEntity projectEntity,
            UUID meetingLeaderId
    ) {
        UUID userId1 = UUID.randomUUID();

        return StandupMeetingEntity.builder()
                .setProject(projectEntity)
                .setId(UUID.randomUUID())
                .setMeetingType(MeetingType.STANDUP)
                .setActive(true)
                .setAttendees(
                        List.of(
                                SampleMeetingAttendees.sampleMeetingAttendeeEntity().setUserId(userId1).build(),
                                SampleMeetingAttendees.sampleMeetingAttendeeEntity()
                                        .setUserId(meetingLeaderId)
                                        .setRole(MeetingRole.MEETING_LEADER)
                                        .build()
                        )
                )
                .setUserIdsOrdered(List.of(meetingLeaderId, userId1))
                .setCurrentAttendee(userId1)
                .setStandupMeetingSettings(
                        StandupMeetingSettingsEmbeddable.builder()
                                .setCountdownPerAttendee(10)
                                .build()
                );

    }

    public static StandupMeeting.Builder sampleStandupMeeting(UUID projectId, UUID meetingLeaderId) {
        UUID userId1 = UUID.randomUUID();

        MeetingAttendee attendee1 = MeetingAttendee.builder()
                .setUserId(userId1)
                .setRole(MeetingRole.ATTENDEE)
                .setState(UserState.ONLINE)
                .build();
        MeetingAttendee attendee2 = MeetingAttendee.builder()
                .setUserId(meetingLeaderId)
                .setRole(MeetingRole.MEETING_LEADER)
                .setState(UserState.ONLINE)
                .build();

        return StandupMeeting.builder()
                .setMeetingType(MeetingType.STANDUP)
                .setActive(true)
                .setAttendees(List.of(attendee2))
                .setOrder(List.of(attendee2, attendee1))
                .setCurrentAttendee(attendee2)
                .setProjectId(projectId);
    }

    public static StandupMeetingInput.Builder sampleStandupMeetingInput(UUID meetingLeaderId) {
        return StandupMeetingInput.builder()
                .setMeetingLeaderId(meetingLeaderId)
                .setStandupMeetingSettings(
                        StandupMeetingSettingsInput.builder()
                                .setCountdownPerAttendee(10)
                                .build()
                );
    }
}
