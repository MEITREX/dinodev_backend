package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;


import de.unistuttgart.iste.meitrex.generated.dto.KnownIcon;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class IconEmbeddable {

    private String    mdiIcon;
    @Column(columnDefinition = "TEXT")
    private String    path;
    @Column(columnDefinition = "TEXT")
    private String    url;
    @Column
    private String emoji;
    @Enumerated
    private KnownIcon knownIcon;

}
