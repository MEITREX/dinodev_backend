package de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius;

import de.unistuttgart.iste.gropius.generated.dto.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GropiusRequests {

    public static ProjectsQueryRequest getProjectRequest(String imsProjectId) {
        return ProjectsQueryRequest.builder()
                .setFilter(GropiusProjectFilterInput.builder()
                        .setId(idFilter(imsProjectId))
                        .build())
                .build();
    }

    static SearchIssuesQueryRequest getIssueQueryRequest(String issueId) {
        return SearchIssuesQueryRequest.builder()
                .setQuery("*") // query must be given
                .setFirst(1)
                .setFilter(GropiusIssueFilterInput.builder()
                        .setId(idFilter(issueId))
                        .build())
                .build();
    }

    static SearchIssuesQueryRequest getIssuesQueryRequest(List<String> issueIds) {
        return SearchIssuesQueryRequest.builder()
                .setQuery("*") // query must be given
                .setFirst(issueIds.size())
                .setFilter(GropiusIssueFilterInput.builder()
                        .setId(GropiusIDFilterInput.builder().setIn(issueIds).build())
                        .build())
                .build();
    }

    static GropiusIDFilterInput idFilter(String id) {
        return GropiusIDFilterInput.builder().setEq(id).build();
    }

    static ChangeIssueTitleMutationRequest getChangeIssueTitleRequest(String issueId, String title) {
        return ChangeIssueTitleMutationRequest.builder()
                .setInput(GropiusChangeIssueTitleInput.builder()
                        .setIssue(issueId)
                        .setTitle(title)
                        .build())
                .build();
    }
}
