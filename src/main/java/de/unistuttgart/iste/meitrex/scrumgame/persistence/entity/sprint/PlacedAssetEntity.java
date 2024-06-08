package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.sprint;

import de.unistuttgart.iste.meitrex.generated.dto.KnownAsset;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@ToString
@Table(name = "placed_asset")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PlacedAssetEntity {

    @GeneratedValue
    @Id
    private UUID       id;
    @Enumerated
    @Setter
    private KnownAsset asset;
    @Setter
    private int        x;
    @Setter
    private int        y;
    @Setter
    private UUID       placedByUserId;

}
