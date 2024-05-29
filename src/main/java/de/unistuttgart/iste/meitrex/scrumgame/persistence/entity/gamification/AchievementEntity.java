package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.gamification;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

@Entity
@Table(name = "achivement")
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementEntity {

    @Id
    @Generated
    @ToString.Include
    private UUID id;

    @Setter
    @ToString.Include
    private String name;

    @Setter
    private String description;

    @Setter
    private int goal;
}
