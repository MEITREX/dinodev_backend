package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.scrumgame.util.VotingStateHolder.VotingState;

import java.util.*;

/**
 * This interface represents a holder for voting states.
 * It provides methods to manage and calculate voting results.
 *
 * @param <T> the type of the object that votes are cast for
 * @param <V> the type of the voting state
 */
public interface VotingStateHolder<T, V extends VotingState<T>> {

    /**
     * Returns a list of all voting states.
     *
     * @return a list of voting states
     */
    List<V> getVotingStates();

    /**
     * Creates a new voting state for the specified object.
     *
     * @param votedFor the object that the vote is cast for
     * @return a new voting state
     */
    V createVotingState(T votedFor);

    /**
     * Adds a vote for the specified object from the specified user.
     * If a voting state for the object does not exist, it is created.
     * The method also ensures that a user can only vote for one object.
     *
     * @param userId   the ID of the user casting the vote
     * @param votedFor the object that the vote is cast for
     */
    default void addVote(UUID userId, T votedFor) {
        V votingState = getVotingStates().stream()
                .filter(state -> state.getVotedFor().equals(votedFor))
                .findFirst()
                .orElseGet(() -> {
                    V newVotingState = createVotingState(votedFor);
                    getVotingStates().add(newVotingState);
                    return newVotingState;
                });

        votingState.getUserVotes().add(userId);

        // remove user votes for other names
        getVotingStates().stream()
                .filter(state -> state != votingState)
                .forEach(state -> state.getUserVotes().remove(userId));
    }

    /**
     * Calculates the result of the voting by finding the object with the most votes.
     *
     * @return an Optional containing the object with the most votes, or an empty Optional if no votes have been cast
     */
    default Optional<T> calculateVotingResult() {
        return getVotingStates().stream()
                .max(Comparator.comparingInt(state -> state.getUserVotes().size()))
                .map(VotingState::getVotedFor);
    }

    /**
     * This interface represents a single voting state.
     * It provides methods to get the object that votes are cast for and the users who have voted for it.
     *
     * @param <T> the type of the object that votes are cast for
     */
    interface VotingState<T> {

        /**
         * Returns the object that votes are cast for.
         *
         * @return the object that votes are cast for
         */
        T getVotedFor();

        /**
         * Returns a set of user IDs who have voted for the object.
         *
         * @return a set of user IDs
         */
        Set<UUID> getUserVotes();
    }
}