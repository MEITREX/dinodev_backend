package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "retrospective_activity")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetrospectiveActivityEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RetrospectiveColumnEntity> columns = new ArrayList<>();

}
