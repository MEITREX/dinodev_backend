package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils.replaceContent;

@Entity
@Table(name = "retrospective_activity")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RetrospectiveActivityEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Setter
    private String name;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RetrospectiveColumnEntity> columns = new ArrayList<>();

    public void setColumns(List<RetrospectiveColumnEntity> columns) {
        this.columns = replaceContent(this.columns, columns);
    }
}
