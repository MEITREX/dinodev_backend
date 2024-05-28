package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Table(name = "achivement_progress")
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(of = "identifier")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementProgressEntity {

    @Id
    @Setter
    @ToString.Include
    private String identifier;

    @Column
    @Setter
    private int progress;

    @Column
    @Setter
    private boolean achieved;
}
