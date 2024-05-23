package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.scrumgame.util.VotingStateHolder.VotingState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class VotingStateHolderTest {


    private StringVotingStateHolder votingStateHolder;
    private UUID                    userId1;
    private UUID                    userId2;
    private UUID                    userId3;

    @BeforeEach
    void setUp() {
        votingStateHolder = new StringVotingStateHolder(new ArrayList<>());
        userId1 = UUID.randomUUID();
        userId2 = UUID.randomUUID();
        userId3 = UUID.randomUUID();
    }

    @Test
    void addVote() {
        // Arrange
        String votedFor = "Test";

        // Act
        votingStateHolder.addVote(userId1, votedFor);

        // Assert
        assertThat(votingStateHolder.getVotingStates(), hasSize(1));
        assertThat(votingStateHolder.getVotingStates().getFirst().votedFor, is(votedFor));
        assertThat(votingStateHolder.getVotingStates().getFirst().userVotes, contains(userId1));
    }

    @Test
    void calculateVotingResult() {
        // Arrange
        String votedFor1 = "Test1";
        String votedFor2 = "Test2";
        votingStateHolder.addVote(userId1, votedFor1);
        votingStateHolder.addVote(userId2, votedFor1);
        votingStateHolder.addVote(userId3, votedFor2);

        // Act
        Optional<String> result = votingStateHolder.calculateVotingResult();

        // Assert
        assertThat(result, is(Optional.of(votedFor1)));
    }

    @Test
    void addVote_DifferentUsers_SameObject() {
        // Arrange
        String votedFor = "Test";

        // Act
        votingStateHolder.addVote(userId1, votedFor);
        votingStateHolder.addVote(userId3, votedFor);

        // Assert
        assertThat(votingStateHolder.getVotingStates(), hasSize(1));
        assertThat(votingStateHolder.getVotingStates().getFirst().getVotedFor(), is(votedFor));
        assertThat(votingStateHolder.getVotingStates().getFirst().getUserVotes(), containsInAnyOrder(userId1, userId3));
    }

    @Test
    void calculateVotingResult_NoVotes() {
        // Act
        Optional<String> result = votingStateHolder.calculateVotingResult();

        // Assert
        assertThat(result, is(Optional.empty()));
    }

    @Test
    void calculateVotingResult_Tie() {
        // Arrange
        String votedFor1 = "Test1";
        String votedFor2 = "Test2";
        votingStateHolder.addVote(userId1, votedFor1);
        votingStateHolder.addVote(userId2, votedFor2);

        // Act
        Optional<String> result = votingStateHolder.calculateVotingResult();

        // Assert
        assertThat(result, either(
                is(Optional.of(votedFor1)))
                .or(is(Optional.of(votedFor2))));
    }

    @Test
    void addVote_MultipleTimes_SameUser() {
        // Arrange
        String votedFor = "Test";

        // Act
        votingStateHolder.addVote(userId1, votedFor);
        votingStateHolder.addVote(userId1, votedFor);

        // Assert
        assertThat(votingStateHolder.getVotingStates(), hasSize(1));
        assertThat(votingStateHolder.getVotingStates().get(0).getVotedFor(), is(votedFor));
        assertThat(votingStateHolder.getVotingStates().get(0).getUserVotes(), contains(userId1));
    }

    record StringVotingState(String votedFor, Set<UUID> userVotes) implements VotingState<String> {

        @Override
        public String getVotedFor() {
            return votedFor;
        }

        @Override
        public Set<UUID> getUserVotes() {
            return userVotes;
        }
    }

    static class StringVotingStateHolder implements VotingStateHolder<String, StringVotingState> {

        private final List<StringVotingState> nameVotingStates;

        StringVotingStateHolder(List<StringVotingState> nameVotingStates) {
            this.nameVotingStates = nameVotingStates;
        }

        @Override
        public List<StringVotingState> getVotingStates() {
            return nameVotingStates;
        }

        @Override
        public StringVotingState createVotingState(String votedFor) {
            return new StringVotingState(votedFor, new HashSet<>());
        }
    }
}