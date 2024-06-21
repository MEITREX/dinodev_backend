package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "retrospective_comment")
@Getter
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class RetrospectiveCommentEntity implements IWithId<UUID> {

    @GeneratedValue
    @Id
    private UUID   id;
    @Setter
    @Column(length = 2000)
    private String content;
    @Setter
    private UUID   authorId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<UUID> thumbsUpBy = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private RetrospectiveColumnEntity column;

    @PreRemove
    private void preRemove() {
        if (column != null) {
            column.getComments().remove(this);
        }
    }

}
