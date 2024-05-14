package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@ToString(of = {"id", "name", "number"})
@Table(name = "sprint")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SprintEntity implements IWithId<UUID> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "end_date")
    private OffsetDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

}
