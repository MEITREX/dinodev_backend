package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting;

import de.unistuttgart.iste.meitrex.generated.dto.MeetingRole;
import de.unistuttgart.iste.meitrex.generated.dto.UserState;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Embeddable
@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class MeetingAttendeeEmbeddable {

    /**
     * The user id of the attendee.
     *
     * @implNote note that theoretically a foreign key to user table / a relation to user entity would make sense
     * but for simplicity we just store the user id here.
     */
    @Column(name = "user_id")
    private UUID userId;

    @Enumerated(EnumType.STRING)
    private MeetingRole role;

    @Enumerated(EnumType.STRING)
    private UserState state;

}
