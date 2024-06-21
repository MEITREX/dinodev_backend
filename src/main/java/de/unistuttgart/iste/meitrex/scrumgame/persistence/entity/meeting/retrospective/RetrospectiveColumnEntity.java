package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective;

import de.unistuttgart.iste.meitrex.common.persistence.IWithId;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils.replaceContent;

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

    @Setter
    private String title;
    @Setter
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RetrospectiveCommentEntity> comments = new ArrayList<>();

    public void setComments(List<RetrospectiveCommentEntity> comments) {
        this.comments = replaceContent(this.comments, comments);
    }
}
