package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.IconEmbeddable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

@Entity
@Table(name = "achievement")
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(of = "identifier")
@Accessors(chain = true)
@Builder(setterPrefix = "set")
@NoArgsConstructor
@AllArgsConstructor
public class AchievementEntity implements IWithId<String> {

    @Id
    @ToString.Include
    private String identifier;

    @Setter
    @ToString.Include
    private String name;

    @Setter
    private String description;

    @Setter
    private int goal;

    @Setter
    private IconEmbeddable icon;

    @OneToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private List<AchievementProgressEntity> progress = new ArrayList<>();

    @Override
    public String getId() {
        return identifier;
    }
}
