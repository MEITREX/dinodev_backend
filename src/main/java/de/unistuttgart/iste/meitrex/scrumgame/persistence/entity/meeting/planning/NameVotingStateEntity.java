package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import de.unistuttgart.iste.meitrex.scrumgame.util.VotingStateHolder.VotingState;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Table(name = "voting_state")
@Entity
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NameVotingStateEntity implements VotingState<String> {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String votedFor;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<UUID> userVotes = new HashSet<>();
}
