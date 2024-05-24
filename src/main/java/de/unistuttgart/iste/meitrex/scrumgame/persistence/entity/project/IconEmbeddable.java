package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;


import jakarta.persistence.Embeddable;
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

    private String mdiIcon;
    private String path;

}
