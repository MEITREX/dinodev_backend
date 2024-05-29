package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Entity
@Table(name = "code_repository_settings")
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class CodeRepositorySettingsEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @Setter
    private List<RepositoryDefinitionEntity> repositories = new ArrayList<>();
}
