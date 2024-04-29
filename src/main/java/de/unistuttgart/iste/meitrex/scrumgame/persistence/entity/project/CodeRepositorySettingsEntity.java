package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Entity
@Table(name = "code_repository_settings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeRepositorySettingsEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "code_repository_name")
    @Setter
    private String codeRepositoryName;
}
