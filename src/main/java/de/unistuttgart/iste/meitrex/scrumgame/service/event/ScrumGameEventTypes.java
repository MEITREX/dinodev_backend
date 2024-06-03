package de.unistuttgart.iste.meitrex.scrumgame.service.event;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Contains predefined event types.
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ScrumGameEventTypes {

    public static final EventType EVENT_REACTION = DefaultEventType.builder()
            .setIdentifier("EVENT_REACTION")
            .setDescription("A user reacted to an event.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setEventSchema(DefaultSchemaDefinition.builder()
                    .setFields(
                            List.of(
                                    DefaultFieldSchemaDefinition.builder()
                                            .setName("reaction")
                                            .setType(AllowedDataType.STRING)
                                            .setDescription("The reaction of the user.")
                                            .setRequired(false)
                                            .build()))
                    .build())
            .setMessageTemplate("reacted to the event with '${reaction}'.")
            .build();

    public static final EventType USER_JOINED = DefaultEventType.builder()
            .setIdentifier("USER_JOINED")
            .setDescription("A user joined the project.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setEventSchema(DefaultSchemaDefinition.builder().build())
            .setMessageTemplate("joined the project! Welcome!")
            .build();

    public static final EventType ACHIEVEMENT_UNLOCKED = DefaultEventType.builder()
            .setIdentifier("ACHIEVEMENT_UNLOCKED")
            .setDescription("An achievement was unlocked.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setEventSchema(DefaultSchemaDefinition.builder()
                    .setFields(
                            List.of(
                                    DefaultFieldSchemaDefinition.builder()
                                            .setName("achievementName")
                                            .setType(AllowedDataType.STRING)
                                            .setDescription("The name of the achievement.")
                                            .setRequired(true)
                                            .build()))
                    .build())
            .setMessageTemplate("unlocked the achievement '${achievementName}'.")
            .build();

    public static final EventType SPRINT_STARTED = DefaultEventType.builder()
            .setIdentifier("SPRINT_STARTED")
            .setDescription("A sprint was started.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setEventSchema(DefaultSchemaDefinition.builder().build())
            .setMessageTemplate("The sprint was started.")
            .build();

    public static final EventType SPRINT_ENDED = DefaultEventType.builder()
            .setIdentifier("SPRINT_ENDED")
            .setDescription("A sprint was ended.")
            .setDefaultVisibility(EventVisibility.PUBLIC)
            .setEventSchema(DefaultSchemaDefinition.builder().build())
            .setMessageTemplate("The sprint was ended.")
            .build();

    public static final EventType SPRINT_PLANNING_STARTED = DefaultEventType.builder()
            .setIdentifier("SPRINT_PLANNING_STARTED")
            .setDescription("A sprint planning was started.")
            .setDefaultVisibility(EventVisibility.INTERNAL)
            .setEventSchema(DefaultSchemaDefinition.builder().build())
            .setMessageTemplate("The sprint planning was started.")
            .build();

    public static final EventType SPRINT_PLANNING_ENDED = DefaultEventType.builder()
            .setIdentifier("SPRINT_PLANNING_ENDED")
            .setDescription("A sprint planning was ended.")
            .setDefaultVisibility(EventVisibility.INTERNAL)
            .setEventSchema(DefaultSchemaDefinition.builder().build())
            .setMessageTemplate("The sprint planning was ended.")
            .build();

    public static final EventType DAILY_SCRUM_STARTED = DefaultEventType.builder()
            .setIdentifier("DAILY_SCRUM_STARTED")
            .setDescription("A daily scrum was started.")
            .setDefaultVisibility(EventVisibility.INTERNAL)
            .setEventSchema(DefaultSchemaDefinition.builder().build())
            .setMessageTemplate("The daily scrum was started.")
            .build();

    public static final EventType DAILY_SCRUM_ENDED = DefaultEventType.builder()
            .setIdentifier("DAILY_SCRUM_ENDED")
            .setDescription("A daily scrum was ended.")
            .setDefaultVisibility(EventVisibility.INTERNAL)
            .setEventSchema(DefaultSchemaDefinition.builder().build())
            .setMessageTemplate("The daily scrum was ended.")
            .build();

    public static final EventType RETROSPECTIVE_STARTED = DefaultEventType.builder()
            .setIdentifier("RETROSPECTIVE_STARTED")
            .setDescription("A retrospective was started.")
            .setDefaultVisibility(EventVisibility.INTERNAL)
            .setEventSchema(DefaultSchemaDefinition.builder().build())
            .setMessageTemplate("The retrospective was started.")
            .build();

    public static final EventType RETROSPECTIVE_ENDED = DefaultEventType.builder()
            .setIdentifier("RETROSPECTIVE_ENDED")
            .setDescription("A retrospective was ended.")
            .setDefaultVisibility(EventVisibility.INTERNAL)
            .setEventSchema(DefaultSchemaDefinition.builder().build())
            .setMessageTemplate("The retrospective was ended.")
            .build();

    public static final EventType XP_GAIN = DefaultEventType.builder()
            .setIdentifier("XP_GAIN")
            .setDescription("A user gained XP.")
            .setDefaultVisibility(EventVisibility.PRIVATE)
            .setEventSchema(DefaultSchemaDefinition.builder().setFields(
                            List.of(
                                    DefaultFieldSchemaDefinition.builder()
                                            .setName("xp")
                                            .setType(AllowedDataType.INTEGER)
                                            .setDescription("The amount of XP gained.")
                                            .setRequired(true)
                                            .build()))
                    .build())
            .setMessageTemplate("gained ${xp} XP!")
            .build();

    public static final EventType LEVEL_UP = DefaultEventType.builder()
            .setIdentifier("LEVEL_UP")
            .setDescription("A user leveled up.")
            .setDefaultVisibility(EventVisibility.PRIVATE)
            .setEventSchema(DefaultSchemaDefinition.builder().setFields(
                            List.of(
                                    DefaultFieldSchemaDefinition.builder()
                                            .setName("newLevel")
                                            .setType(AllowedDataType.INTEGER)
                                            .setDescription("The new level of the user.")
                                            .setRequired(true)
                                            .build()))
                    .build())
            .setMessageTemplate("leveled up to level ${newLevel}!")
            .build();

    public static Iterable<EventType> allEventTypes() {
        return List.of(
                XP_GAIN,
                LEVEL_UP,
                EVENT_REACTION,
                USER_JOINED,
                ACHIEVEMENT_UNLOCKED,
                SPRINT_STARTED,
                SPRINT_ENDED,
                SPRINT_PLANNING_STARTED,
                SPRINT_PLANNING_ENDED,
                DAILY_SCRUM_STARTED,
                DAILY_SCRUM_ENDED,
                RETROSPECTIVE_STARTED,
                RETROSPECTIVE_ENDED);
    }
}
