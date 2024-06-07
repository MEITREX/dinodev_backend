package de.unistuttgart.iste.meitrex.scrumgame.controller.user;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.AchievementService;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.UserStatsService;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.UserInProjectService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class UserInProjectController {

    private final UserInProjectService userInProjectService;
    private final UserStatsService userStatsService;
    private final AchievementService achievementService;

    @SchemaMapping
    @Nullable
    public UserInProject userInProject(GlobalUser globalUser, @Argument UUID projectId) {
        return userInProjectService
                .findUserInProject(globalUser.getId(), projectId)
                .orElse(null);
    }

    @SchemaMapping
    public List<UserInProject> userInProjects(GlobalUser globalUser) {
        return userInProjectService.getUserInProjectsByUserId(globalUser.getId());
    }

    @SchemaMapping
    public List<UserInProject> users(Project project) {
        return userInProjectService.getUsersInProjectByProjectId(project.getId());
    }

    @SchemaMapping
    public List<AchievementProgress> achievements(UserInProject userInProject) {
        return achievementService.getForUserInProject(userInProject.getUserId(), userInProject.getProjectId());
    }

    @SchemaMapping
    public UserInProject currentUser(Project project) {
        return userInProjectService
                .findCurrentUserInProject(project.getId())
                .orElse(null);
    }

    @SchemaMapping
    public UserStats userStats(UserInProject userInProject) {
        return userStatsService.findOrInitUserStats(userInProject.getUserId(), userInProject.getProjectId());
    }

    @SchemaMapping
    public List<UserInProject> assignees(Issue issue) {
        return userInProjectService.getUsers(issue.getProjectId(), issue.getAssigneeIds());
    }

    @MutationMapping
    public UserInProject joinProject(@Argument UUID projectId) {
        return userInProjectService.joinProject(projectId);
    }

    @SchemaMapping
    public boolean resetUserStats(ProjectMutation projectMutation) {
        userStatsService.resetUserStatsInProject(projectMutation.getProject().getId());
        return true;
    }

    @SchemaMapping
    public boolean resetAchievements(ProjectMutation projectMutation) {
        achievementService.resetAchievements(projectMutation.getProject().getId());
        return true;
    }

}
