package de.unistuttgart.iste.meitrex.scrumgame.controller.user;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.GlobalUserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GlobalUserController {

    private final GlobalUserService userService;

    @QueryMapping
    public List<GlobalUser> globalUsers() {
        return userService.getAllGlobalUsers();
    }

    @QueryMapping
    @Nullable
    public GlobalUser globalUser(@Argument UUID id) {
        return userService.findGlobalUser(id).orElse(null);
    }

    @QueryMapping
    @Nullable
    public GlobalUser currentUser() {
        return userService.findCurrentUser().orElse(null);
    }

    @BatchMapping
    public List<GlobalUser> user(List<UserInProject> users) {
        return userService.findUsersBatched(users.stream().map(UserInProject::getUserId).toList());
    }

    @SchemaMapping(typeName = "DefaultEvent", field = "user")
    public GlobalUser userOfDefaultEvent(DefaultEvent event) {
        return userService.findGlobalUser(event.getUserId()).orElse(null);
    }

    // remark: BatchMapping doesn't work with subscriptions for some reason (Only costed a few hours of debugging)
    @SchemaMapping(typeName = "MeetingAttendee", field = "user")
    public GlobalUser userOfMeetingAttendee(MeetingAttendee meetingAttendee) {
        return userService.findGlobalUser(meetingAttendee.getUserId()).orElse(null);
    }

    @SchemaMapping(typeName = "Vote", field = "user")
    public GlobalUser userOfVote(Vote vote) {
        return userService.findGlobalUser(vote.getUserId()).orElse(null);
    }

    @SchemaMapping(typeName = "Reaction", field = "user")
    public GlobalUser userOfReaction(Reaction reaction) {
        return userService.findGlobalUser(reaction.getUserId()).orElse(null);
    }

    @SchemaMapping(typeName = "RetrospectiveMeeting", field = "goldMedalUser")
    public GlobalUser userOfGoldMedalUser(RetrospectiveMeeting retrospectiveMeeting) {
        return userService.findGlobalUser(retrospectiveMeeting.getGoldMedalUserId()).orElse(null);
    }

    @SchemaMapping(typeName = "RetrospectiveMeeting", field = "silverMedalUser")
    public GlobalUser userOfSilverMedalUser(RetrospectiveMeeting retrospectiveMeeting) {
        return userService.findGlobalUser(retrospectiveMeeting.getSilverMedalUserId()).orElse(null);
    }

    @SchemaMapping(typeName = "RetrospectiveMeeting", field = "bronzeMedalUser")
    public GlobalUser userOfBronzeMedalUser(RetrospectiveMeeting retrospectiveMeeting) {
        return userService.findGlobalUser(retrospectiveMeeting.getBronzeMedalUserId()).orElse(null);
    }

    @MutationMapping
    public GlobalUser grantRole(@Argument UUID userId, @Argument String roleName) {
        return userService.grantRole(userId, roleName);
    }

    @MutationMapping
    public GlobalUser revokeRole(@Argument UUID userId, @Argument String roleName) {
        return userService.revokeRole(userId, roleName);
    }

    @MutationMapping
    public GlobalUser register(@Argument CreateGlobalUserInput input) {
        return userService.registerNewUser(input);
    }

    @MutationMapping
    public GlobalUser updateGlobalUser(@Argument UUID id, @Argument UpdateGlobalUserInput input) {
        return userService.updateGlobalUser(id, input);
    }
}
