package de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius;

import de.unistuttgart.iste.meitrex.generated.dto.IssueState;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Converts IMS issue states to IssueState objects.
 */
public class IssueStateMapping {

    private final Map<String, IssueState> issueStateMap;

    public IssueStateMapping(List<IssueState> issueStates) {
        this.issueStateMap = issueStates.stream()
                .collect(Collectors.toMap(IssueState::getImsStateId, Function.identity()));
    }

    public IssueState getIssueState(String imsStateId) {
        if (!issueStateMap.containsKey(imsStateId)) {
            throw new IllegalArgumentException("Unknown IMS state ID: " + imsStateId);
        }
        return issueStateMap.get(imsStateId);
    }

    public String getIssueStateId(String issueName) {
        return issueStateMap.entrySet().stream()
                .filter(entry -> entry.getValue().getName().equals(issueName))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

}
