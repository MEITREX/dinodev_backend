package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class StandupMeetingSettingsEmbeddable {

    @Nullable
    private Integer countdownPerAttendee;

}
