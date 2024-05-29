package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivateUserInfoEmbeddable {

    private int xp;

    private int level;
}
