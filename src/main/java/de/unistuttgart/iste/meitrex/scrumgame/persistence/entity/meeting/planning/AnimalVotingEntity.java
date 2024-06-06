package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import de.unistuttgart.iste.meitrex.generated.dto.Animal;
import de.unistuttgart.iste.meitrex.scrumgame.util.VotingStateHolder;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

import static de.unistuttgart.iste.meitrex.scrumgame.util.PersistenceUtils.replaceContent;

@Entity
@Table(name = "animal_voting")
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalVotingEntity implements VotingStateHolder<Animal, AnimalVotingStateEntity> {

    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<Animal> votableAnimals = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AnimalVotingStateEntity> animalVotingStates = new ArrayList<>();

    @Column
    private boolean finished;

    @Column
    @Nullable
    private Animal votingResult;

    public void finishVoting() {
        votingResult = calculateVotingResult().orElse(null);
        finished = true;
    }

    @Override
    public List<AnimalVotingStateEntity> getVotingStates() {
        return animalVotingStates;
    }

    @Override
    public AnimalVotingStateEntity createVotingState(Animal votedFor) {
        return AnimalVotingStateEntity.builder().setVotedFor(votedFor).build();
    }

    public void setAnimalVotingStates(List<AnimalVotingStateEntity> animalVotingStates) {
        this.animalVotingStates = replaceContent(this.animalVotingStates, animalVotingStates);
    }

    public void setVotableAnimals(List<Animal> votableAnimals) {
        this.votableAnimals = replaceContent(this.votableAnimals, votableAnimals);
    }
}
