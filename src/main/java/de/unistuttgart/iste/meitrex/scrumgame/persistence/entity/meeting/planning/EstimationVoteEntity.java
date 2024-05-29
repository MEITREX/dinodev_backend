package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import de.unistuttgart.iste.meitrex.generated.dto.TShirtSizeEstimation;
import de.unistuttgart.iste.meitrex.scrumgame.util.VotingStateHolder.VotingState;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

@Entity
@Table(name = "estimation_vote")
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
public class EstimationVoteEntity implements VotingState<TShirtSizeEstimation> {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    @Enumerated(EnumType.STRING)
    @Setter
    private TShirtSizeEstimation votedFor;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<UUID> userVotes = new HashSet<>();
}
