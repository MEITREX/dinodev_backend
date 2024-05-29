package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.generated.dto.IssueState;
import de.unistuttgart.iste.meitrex.generated.dto.IssueStateType;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class StateUtils {

    public static boolean isMovedOutOfSprint(IssueState from, IssueState to) {
        return to.getType() == IssueStateType.BACKLOG
               && from.getType() != IssueStateType.BACKLOG;
    }

    public static boolean isMovedIntoSprint(IssueState from, IssueState to) {
        return from.getType() == IssueStateType.BACKLOG
               && to.getType() != IssueStateType.BACKLOG
               && to.getType() != IssueStateType.DONE;
    }

    public static boolean isMovedToDone(IssueState from, IssueState to) {
        return (to.getType() == IssueStateType.DONE_SPRINT || to.getType() == IssueStateType.DONE)
               && from.getType() != IssueStateType.DONE_SPRINT
               && from.getType() != IssueStateType.DONE;
    }

    public static boolean isReopened(IssueState from, IssueState to) {
        return (from.getType() == IssueStateType.DONE || from.getType() == IssueStateType.DONE_SPRINT)
               && to.getType() != IssueStateType.DONE
               && to.getType() != IssueStateType.DONE_SPRINT;
    }

    public static boolean isMovedToInProgress(IssueState from, IssueState to) {
        return to.getType() == IssueStateType.IN_PROGRESS
               && from.getType() != IssueStateType.IN_PROGRESS;
    }

}
