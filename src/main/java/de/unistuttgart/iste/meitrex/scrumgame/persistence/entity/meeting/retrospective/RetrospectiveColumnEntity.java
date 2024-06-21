package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "retrospective_column")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetrospectiveColumnEntity implements IWithId<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RetrospectiveCommentEntity> comments = new ArrayList<>();

}
