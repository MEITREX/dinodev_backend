package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

@Entity
@Table(name = "repository_definition")
@Getter
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder(setterPrefix = "set")
@ToString(of = {"id", "name"})
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDefinitionEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Nullable
    @Setter
    private String name;
    @Nullable
    @Setter
    @Column(name = "repository_url")
    private String url;

    @Setter
    private IconEmbeddable icon;
}
