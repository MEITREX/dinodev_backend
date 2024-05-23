package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import de.unistuttgart.iste.meitrex.scrumgame.util.VotingStateHolder;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "name_voting")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NameVotingEntity implements VotingStateHolder<String, NameVotingStateEntity> {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> votableNames = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<NameVotingStateEntity> nameVotingStates = new ArrayList<>();

    @Column
    private boolean finished;

    @Column
    @Nullable
    private String votingResult;

    public void finishVoting() {
        votingResult = calculateVotingResult().orElse(null);
        finished = true;
    }

    @Override
    public List<NameVotingStateEntity> getVotingStates() {
        return nameVotingStates;
    }

    @Override
    public NameVotingStateEntity createVotingState(String votedFor) {
        return NameVotingStateEntity.builder().setVotedFor(votedFor).build();
    }
}
