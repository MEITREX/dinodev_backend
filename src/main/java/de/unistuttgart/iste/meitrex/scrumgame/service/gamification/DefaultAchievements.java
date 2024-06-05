package de.unistuttgart.iste.meitrex.scrumgame.service.gamification;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification.AchievementEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.IconEmbeddable;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DefaultAchievements {

    public static final AchievementEntity ACHIEVEMENT_NIGHT_OWL = AchievementEntity.builder()
            .setIdentifier("NIGHT_OWL")
            .setName("Night Owl")
            .setDescription("Finish an issue after 8pm.")
            .setGoal(1)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("🦉")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_EARLY_BIRD = AchievementEntity.builder()
            .setIdentifier("EARLY_BIRD")
            .setName("Early Bird")
            .setDescription("Finish an issue before 9am.")
            .setGoal(1)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("🐦")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_FIRST_STEPS = AchievementEntity.builder()
            .setIdentifier("FIRST_STEPS")
            .setName("First Steps")
            .setDescription("Complete your first issue.")
            .setGoal(1)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("👣")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_GETTING_STARTED = AchievementEntity.builder()
            .setIdentifier("GETTING_STARTED")
            .setName("Getting Started")
            .setDescription("Complete 10 issues.")
            .setGoal(10)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("🚀")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_QUICK_BROWN_FOX = AchievementEntity.builder()
            .setIdentifier("QUICK_BROWN_FOX")
            .setName("Quick Brown Fox")
            .setDescription("Complete 5 issues on the first three days of the sprint.")
            .setGoal(5)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("🦊")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_TASK_CHAMPION = AchievementEntity.builder()
            .setIdentifier("TASK_CHAMPION")
            .setName("Task Champion")
            .setDescription("Complete 25 issues.")
            .setGoal(25)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("🤺")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_TASK_LEGEND = AchievementEntity.builder()
            .setIdentifier("TASK_LEGEND")
            .setName("Task Legend")
            .setDescription("Complete 100 issues.")
            .setGoal(100)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("🌟")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_TEAM_PLAYER = AchievementEntity.builder()
            .setIdentifier("TEAM_PLAYER")
            .setName("Team Player")
            .setDescription("Complete 5 issues with more than one assignee.")
            .setGoal(5)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("👥")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_SOCIAL_BUTTERFLY = AchievementEntity.builder()
            .setIdentifier("SOCIAL_BUTTERFLY")
            .setName("Social Butterfly")
            .setDescription("React to 100 events with a ♥️.")
            .setGoal(100)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("🦋")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_REVIEWER = AchievementEntity.builder()
            .setIdentifier("REVIEWER")
            .setName("Reviewer")
            .setDescription("Review 10 pull requests.")
            .setGoal(10)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("👀")
                    .build())
            .build();

    public static final AchievementEntity ACHIEVEMENT_CRITIC = AchievementEntity.builder()
            .setIdentifier("CRITIC")
            .setName("Critic")
            .setDescription("Request changes on 10 pull requests.")
            .setGoal(10)
            .setIcon(IconEmbeddable.builder()
                    .setEmoji("🔍")
                    .build())
            .build();

    public static final List<AchievementEntity> DEFAULT_ACHIEVEMENTS = List.of(
            ACHIEVEMENT_NIGHT_OWL,
            ACHIEVEMENT_EARLY_BIRD,
            ACHIEVEMENT_FIRST_STEPS,
            ACHIEVEMENT_GETTING_STARTED,
            ACHIEVEMENT_QUICK_BROWN_FOX,
            ACHIEVEMENT_TASK_CHAMPION,
            ACHIEVEMENT_TASK_LEGEND,
            ACHIEVEMENT_TEAM_PLAYER,
            ACHIEVEMENT_SOCIAL_BUTTERFLY,
            ACHIEVEMENT_REVIEWER,
            ACHIEVEMENT_CRITIC
    );
}
