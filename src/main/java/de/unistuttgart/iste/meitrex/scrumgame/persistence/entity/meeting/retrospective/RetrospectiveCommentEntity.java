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
    private String content;
    private UUID   authorId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<UUID> thumbsUpBy = new HashSet<>();


}
