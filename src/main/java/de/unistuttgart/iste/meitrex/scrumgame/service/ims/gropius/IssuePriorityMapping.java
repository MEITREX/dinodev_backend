package de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius;

import de.unistuttgart.iste.meitrex.generated.dto.IssuePriority;
import lombok.RequiredArgsConstructor;

import java.util.*;

/**
 * Maps issue priorities between the Scrum Game and the IMS.
 */
@RequiredArgsConstructor
public class IssuePriorityMapping {

    private final Map<String, IssuePriority> issuePriorityMap;


    public IssuePriority getIssuePriority(String imsPriorityId) {
        return issuePriorityMap.get(imsPriorityId);
    }

    public String getIssuePriorityId(IssuePriority issuePriority) {
        return issuePriorityMap.entrySet().stream()
                .filter(entry -> entry.getValue() == issuePriority)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(issuePriority.toString());
    }
}
