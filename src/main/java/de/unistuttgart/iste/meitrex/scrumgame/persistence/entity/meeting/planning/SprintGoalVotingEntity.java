package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "name_voting")
@EqualsAndHashCode(of = "id")
@Getter
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class SprintGoalVotingEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<String> sprintIssueIds = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    // HINT no longer used, can be removed
    private Set<String> nonSprintIssueIds = new HashSet<>();

    @Column
    private boolean finished;

    public void finishVoting() {
        finished = true;
    }

}
