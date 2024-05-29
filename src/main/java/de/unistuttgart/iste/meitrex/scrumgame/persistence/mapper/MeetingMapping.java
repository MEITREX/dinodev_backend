package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.RetrospectiveMeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning.AnimalVotingStateEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning.EstimationVoteEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning.NameVotingStateEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning.PlanningMeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.standup.StandupMeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.util.MeitrexConverters;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;
import org.modelmapper.Provider;

import java.util.*;

@Slf4j
public class MeetingMapping implements Module {

    @Override
    public void setupModule(ModelMapper modelMapper) {

        setupPlanningMeetingMapping(modelMapper);

        modelMapper.emptyTypeMap(MeetingEntity.class, Meeting.class)
                .setProvider(getMeetingProvider(modelMapper));
        modelMapper.emptyTypeMap(PlanningMeetingEntity.class, Meeting.class)
                .setProvider(getMeetingProvider(modelMapper));
        modelMapper.emptyTypeMap(RetrospectiveMeetingEntity.class, Meeting.class)
                .setProvider(getMeetingProvider(modelMapper));
        modelMapper.emptyTypeMap(StandupMeetingEntity.class, Meeting.class)
                .setProvider(getMeetingProvider(modelMapper));
    }

    private void setupPlanningMeetingMapping(ModelMapper modelMapper) {

        modelMapper.emptyTypeMap(NameVotingStateEntity.class, NameVotingState.class)
                // computed field totalVotes
                .addMappings(mapper -> mapper
                        .using(MeitrexConverters.collectionToSize())
                        .map(NameVotingStateEntity::getUserVotes, NameVotingState::setTotalVotes))
                // animal votes field has to be converted
                .addMappings(mapper -> mapper
                        .using(getVoteConverter())
                        .map(NameVotingStateEntity::getUserVotes, NameVotingState::setUserVotes))
                .implicitMappings();

        modelMapper.emptyTypeMap(AnimalVotingStateEntity.class, AnimalVotingState.class)
                // computed field totalVotes
                .addMappings(mapper -> mapper
                        .using(MeitrexConverters.collectionToSize())
                        .map(AnimalVotingStateEntity::getUserVotes, AnimalVotingState::setTotalVotes))
                // animal votes field has to be converted
                .addMappings(mapper -> mapper
                        .using(getVoteConverter())
                        .map(AnimalVotingStateEntity::getUserVotes, AnimalVotingState::setUserVotes))
                .implicitMappings();

        modelMapper.emptyTypeMap(EstimationVoteEntity.class, EstimationVote.class)
                // computed field totalVotes
                .addMappings(mapper -> mapper
                        .using(MeitrexConverters.collectionToSize())
                        .map(EstimationVoteEntity::getUserVotes, EstimationVote::setTotalVotes))
                // animal votes field has to be converted
                .addMappings(mapper -> mapper
                        .using(getVoteConverter())
                        .map(EstimationVoteEntity::getUserVotes, EstimationVote::setUserVotes))
                .implicitMappings();

        modelMapper.createTypeMap(PlanningMeetingEntity.class, PlanningMeeting.class)
                .addMapping(entity -> entity.getProject().getId(), PlanningMeeting::setProjectId)
                .setPostConverter(context -> {
                    // set the planning meeting reference to the sub entities
                    PlanningMeeting result = context.getDestination();
                    result.getSprintGoalVoting().setPlanningMeeting(result);
                    result.getIssueEstimation().setPlanningMeeting(result);
                    return result;
                });

        modelMapper.createTypeMap(StandupMeetingEntity.class, StandupMeeting.class)
                .addMapping(entity -> entity.getProject().getId(), StandupMeeting::setProjectId);
    }

    private Converter<List<UUID>, List<Vote>> getVoteConverter() {
        return MeitrexConverters.listConverter(uuid -> Vote.builder().setUserId(uuid).build());
    }

    // provider that maps the MeetingEntity to the correct Meeting subclass
    private static Provider<Meeting> getMeetingProvider(ModelMapper modelMapper) {
        return request -> {
            Object source = request.getSource();
            return switch (source) {
                case PlanningMeetingEntity planningMeetingEntity ->
                        modelMapper.map(planningMeetingEntity, PlanningMeeting.class);
                case RetrospectiveMeetingEntity retrospectiveMeetingEntity ->
                        modelMapper.map(retrospectiveMeetingEntity, RetrospectiveMeeting.class);
                case StandupMeetingEntity standupMeetingEntity ->
                        modelMapper.map(standupMeetingEntity, StandupMeeting.class);
                default -> throw new IllegalArgumentException("Unknown meeting type");
            };
        };
    }
}
