package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private List<RepositoryDefinitionEntity> repositories = new ArrayList<>();

    public void setRepositories(List<RepositoryDefinitionEntity> repositories) {
        if (this.repositories == null) {
            this.repositories = new ArrayList<>();
        }
        this.repositories.clear();
        this.repositories.addAll(repositories);
    }
}
