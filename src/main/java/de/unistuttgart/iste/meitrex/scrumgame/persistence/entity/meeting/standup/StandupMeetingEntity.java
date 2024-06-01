package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<UUID> userIdsOrdered = new ArrayList<>();

    private UUID currentAttendee;

    public List<UUID> getOrder() {
        return userIdsOrdered;
    }

    public void setOrder(List<UUID> order) {
        this.userIdsOrdered = order;
    }

    public StandupMeetingSettingsEmbeddable getStandupMeetingSettings() {
        if (standupMeetingSettings == null) {
            standupMeetingSettings = new StandupMeetingSettingsEmbeddable(null);
        }
        return standupMeetingSettings;
    }
}
