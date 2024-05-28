package de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius;

import de.unistuttgart.iste.meitrex.generated.dto.IssueState;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Converts IMS issue states to IssueState objects.
 */
public class IssueStateConverter {

    private final Map<String, IssueState> issueStateMap;

    public IssueStateConverter(List<IssueState> issueStates) {
        this.issueStateMap = issueStates.stream()
                .collect(Collectors.toMap(IssueState::getImsStateId, Function.identity()));
    }

    public IssueState getIssueState(String imsStateId) {
        return issueStateMap.get(imsStateId);
    }

}
