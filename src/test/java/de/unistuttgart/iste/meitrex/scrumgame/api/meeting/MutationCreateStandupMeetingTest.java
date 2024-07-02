package de.unistuttgart.iste.meitrex.scrumgame.api.meeting;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleGlobalUsers;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjects;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleStandupMeetings;
import de.unistuttgart.iste.meitrex.scrumgame.fragments.MeetingFragments;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup.StandupMeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserInProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.UserProjectId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.ProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.StandupMeetingRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserInProjectRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.meeting.StandupMeetingService;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.*;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static de.unistuttgart.iste.meitrex.scrumgame.matchers.StandupMeetingMatcher.matchingStandupMeetingEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@GraphQlApiTest
@ActiveProfiles("test")
class MutationCreateStandupMeetingTest {

    @Autowired
    private ProjectRepository        projectRepository;
    @Autowired
    private StandupMeetingRepository standupMeetingRepository;
    @Autowired
    private GlobalUserRepository     globalUserRepository;
    @Autowired
    private UserInProjectRepository  userInProjectRepository;
    @Autowired
    private StandupMeetingService    standupMeetingService;

    @Test
    @Transactional
    @Commit
    void testCreateStandupMeeting(GraphQlTester graphQlTester) {
        // arrange
        ProjectEntity project = createProject();
        GlobalUserEntity user1 = createGlobalUser();
        GlobalUserEntity user2 = createGlobalUser();
        addUserToProject(project, user1);
        addUserToProject(project, user2);

        StandupMeetingInput input = SampleStandupMeetings.sampleStandupMeetingInput(user2.getId()).build();

        StepVerifier stepVerifier = StepVerifier.create(standupMeetingService
                        .getStandupMeetingUpdatedSubscription(project.getId()))
                .expectNextMatches(standupMeeting
                        -> standupMeeting.getActive() && standupMeeting.getProjectId().equals(project.getId()))
                .thenCancel()
                .verifyLater();

        // act
        StandupMeeting standupMeeting = graphQlTester.document(getCreateStandupMeetingMutation())
                .variables(Map.of("projectId", project.getId(), "input", input))
                .execute()
                .path("mutateProject.createStandupMeeting")
                .entity(StandupMeeting.class)
                .get();

        // assert
        assertThat(standupMeeting.getProjectId(), is(project.getId()));
        assertThat(standupMeeting.getActive(), is(true));
        assertThat(standupMeeting.getMeetingType(), is(MeetingType.STANDUP));

        MeetingAttendee attendee2 = new MeetingAttendee(user2.getId(), UserState.ONLINE, MeetingRole.MEETING_LEADER);
        assertThat(standupMeeting.getAttendees(), containsInAnyOrder(attendee2));
        assertThat(standupMeeting.getOrder(), is(empty()));
        assertThat(standupMeeting.getCurrentAttendee(), is(nullValue()));

        assertThat(standupMeeting.getStandupMeetingSettings().getCountdownPerAttendee(), is(10));

        // db assertions
        StandupMeetingEntity standupMeetingEntity
                = standupMeetingRepository.findFirstByProjectIdAndActive(project.getId(), true).orElseThrow();

        assertThat(standupMeeting, matchingStandupMeetingEntity(standupMeetingEntity));

        // verify subscription
        stepVerifier.verify(Duration.ofSeconds(10));
    }

    private void addUserToProject(ProjectEntity project, GlobalUserEntity user1) {
        UserInProjectEntity userInProject1 = UserInProjectEntity.builder()
                .id(new UserProjectId(user1.getId(), project.getId()))
                .project(project)
                .user(user1)
                .build();
        userInProjectRepository.save(userInProject1);
    }

    private @NotNull ProjectEntity createProject() {
        ProjectEntity project = SampleProjects.sampleProjectBuilder().build();
        project = projectRepository.save(project);
        return project;
    }

    private @NotNull GlobalUserEntity createGlobalUser() {
        GlobalUserEntity user1 = SampleGlobalUsers.sampleGlobalUserEntityBuilder().build();
        user1 = globalUserRepository.save(user1);
        return user1;
    }


    private String getCreateStandupMeetingMutation() {
        return gql("""
                           mutation createStandupMeeting($projectId: UUID!, $input: StandupMeetingInput!) {
                               mutateProject(id: $projectId) {
                                   createStandupMeeting(input: $input) {
                                       ...StandupMeetingFragment
                                   }
                               }
                           }
                           """ + MeetingFragments.STANDUP_MEETING_FRAGMENT);
    }
}
