package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import de.unistuttgart.iste.meitrex.generated.dto.PlanningMeetingPage;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.MeetingEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "planning_meeting")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PlanningMeetingEntity extends MeetingEntity {

    @Embedded
    private PlanningSettingsEmbeddable planningSettings;

    @Column(name = "current_page", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PlanningMeetingPage currentPage = PlanningMeetingPage.INFORMATION;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Builder.Default
    private AnimalVotingEntity animalVoting = AnimalVotingEntity.builder().build();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Builder.Default
    private NameVotingEntity nameVoting = NameVotingEntity.builder().build();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Builder.Default
    private IssueEstimationEntity issueEstimation = IssueEstimationEntity.builder().build();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Builder.Default
    private SprintGoalVotingEntity sprintGoalVoting = SprintGoalVotingEntity.builder().build();
}
