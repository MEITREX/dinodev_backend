package de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius;

import de.unistuttgart.iste.meitrex.generated.dto.IssueTypeConfiguration;

import java.util.*;
import java.util.stream.*;

public class IssueTypeMapping {

    private final Map<String, String> imsIssueIdToIssueNameMap;

    public IssueTypeMapping(List<IssueTypeConfiguration> issueTypes) {
        imsIssueIdToIssueNameMap = issueTypes.stream()
                .collect(Collectors.toMap(
                        IssueTypeConfiguration::getImsTypeId,
                        IssueTypeConfiguration::getName));
    }

    public String getIssueTypeName(String imsIssueId) {
        return imsIssueIdToIssueNameMap.get(imsIssueId);
    }

    public String getIssueTypeId(String issueTypeName) {
        return imsIssueIdToIssueNameMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(issueTypeName))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

}
