package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.planning;

import de.unistuttgart.iste.meitrex.generated.dto.Animal;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Animals that are already unlocked in the first sprint.
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DefaultAnimals {

    public static final Set<Animal> DEFAULT_ANIMALS = EnumSet.of(Animal.DODO, Animal.TREX);

}
