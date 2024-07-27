package de.unistuttgart.iste.meitrex.scrumgame.mapping;

import de.unistuttgart.iste.meitrex.generated.dto.StandupMeeting;
import de.unistuttgart.iste.meitrex.scrumgame.config.ModelMapperConfiguration;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleProjects;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleStandupMeetings;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup.StandupMeetingEntity;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.*;

import static de.unistuttgart.iste.meitrex.scrumgame.matchers.StandupMeetingMatcher.matchingStandupMeetingEntity;
import static org.hamcrest.MatcherAssert.assertThat;

class StandupMeetingMappingTest {

    private final ModelMapper modelMapper = new ModelMapperConfiguration().modelMapper();

    @Test
    void testMapEntityToDto() {
        // arrange
        StandupMeetingEntity standupMeetingEntity = SampleStandupMeetings.sampleStandupMeetingEntity(
                SampleProjects.sampleProjectBuilder().build(),
                UUID.randomUUID()).build();

        // act
        StandupMeeting standupMeeting = modelMapper.map(standupMeetingEntity, StandupMeeting.class);

        // assert
        assertThat(standupMeeting, matchingStandupMeetingEntity(standupMeetingEntity));
    }
}
