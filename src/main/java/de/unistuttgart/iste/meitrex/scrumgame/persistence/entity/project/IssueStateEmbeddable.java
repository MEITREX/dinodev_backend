package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import de.unistuttgart.iste.meitrex.generated.dto.IssueStateType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueStateEmbeddable {

    @Column
    private String name;

    @Column
    private String imsStateId;

    @Column
    @Enumerated
    private IssueStateType type;

}
