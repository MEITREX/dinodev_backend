package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

import static de.unistuttgart.iste.meitrex.scrumgame.util.PersistenceUtils.replaceContent;

@Entity
@Table(name = "dod_item")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefinitionOfDoneItemEntity {

    @Id
    @GeneratedValue
    private UUID    id;
    private String  text;
    private boolean required;

    @OneToMany(fetch = FetchType.EAGER,
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<DefinitionOfDoneItemEntity> implies = new ArrayList<>();

    public void setImplies(List<DefinitionOfDoneItemEntity> implies) {
        this.implies = replaceContent(this.implies, implies);
    }
}
