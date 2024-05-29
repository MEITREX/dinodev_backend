package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import de.unistuttgart.iste.meitrex.generated.dto.TShirtSizeEstimation;
import de.unistuttgart.iste.meitrex.scrumgame.util.VotingStateHolder;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

@Entity
@Table(name = "issue_estimation")
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueEstimationEntity implements VotingStateHolder<TShirtSizeEstimation, EstimationVoteEntity> {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private boolean finished;

    @Column
    @Enumerated(EnumType.STRING)
    @Nullable
    @Setter
    private TShirtSizeEstimation finalResult;

    @Column
    @Setter
    @Nullable
    private Integer countdownSeconds;

    @Column
    @Setter
    private String issueId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @Setter
    private List<EstimationVoteEntity> votes = new ArrayList<>();

    public void resetVotes() {
        votes.clear();
        finished = false;
        finalResult = null;
    }

    public void finishVoting() {
        // in contrast to other voting types, we don't need to calculate the final result here
        // as it has to be manually confirmed by the meeting leader
        finished = true;
        countdownSeconds = null;
    }

    @Override
    public List<EstimationVoteEntity> getVotingStates() {
        return votes;
    }

    @Override
    public EstimationVoteEntity createVotingState(TShirtSizeEstimation votedFor) {
        return EstimationVoteEntity.builder().setVotedFor(votedFor).build();
    }
}
