package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.generated.dto.Issue;
import de.unistuttgart.iste.meitrex.generated.dto.IssueStateInBoard;
import de.unistuttgart.iste.meitrex.generated.dto.Sprint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class ImsServiceExtension {

    private final ImsService baseImsService;

    public Map<IssueStateInBoard, List<Issue>> getIssuesByStates(List<IssueStateInBoard> states) {
        Map<IssueStateInBoard, List<Issue>> resultMap = new HashMap<>();

        for (IssueStateInBoard state : states) {
            List<Issue> issues = baseImsService.getIssues(state.getProjectBoard().getProject());
            List<Issue> stateIssues = issues.stream()
                    .filter(issue -> Objects.equals(issue.getState().getImsStateId(), state.getState().getImsStateId()))
                    .collect(Collectors.toList());
            resultMap.put(state, stateIssues);
        }

        return resultMap;
    }

    public Map<Sprint, List<Issue>> getIssuesBySprints(List<Sprint> sprints) {
        Map<Sprint, List<Issue>> resultMap = new HashMap<>();

        for (Sprint sprint : sprints) {
            List<Issue> issues = baseImsService.getIssues(sprint.getProject());
            List<Issue> sprintIssues = issues.stream()
                    .filter(issue -> Objects.equals(issue.getSprintNumber(), sprint.getNumber()))
                    .collect(Collectors.toList());
            resultMap.put(sprint, sprintIssues);
        }

        return resultMap;
    }

}
