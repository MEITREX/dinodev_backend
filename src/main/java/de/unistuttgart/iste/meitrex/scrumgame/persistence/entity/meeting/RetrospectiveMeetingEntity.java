package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "retro_meeting")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RetrospectiveMeetingEntity extends MeetingEntity {

    @Column(name = "next_page_allowed")
    @Builder.Default
    boolean nextPageAllowed = false;
}
