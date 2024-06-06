package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import de.unistuttgart.iste.meitrex.generated.dto.Animal;
import de.unistuttgart.iste.meitrex.scrumgame.util.VotingStateHolder.VotingState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Table(name = "animal_voting_state")
@Entity
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnimalVotingStateEntity implements VotingState<Animal> {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    @Enumerated(EnumType.STRING)
    private Animal votedFor;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<UUID> userVotes = new HashSet<>();
}
