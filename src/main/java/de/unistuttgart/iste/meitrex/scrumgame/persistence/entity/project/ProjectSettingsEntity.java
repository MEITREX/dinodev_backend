package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Entity
@Table(name = "project_settings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectSettingsEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "code_repository_settings_id")
    @Setter
    private CodeRepositorySettingsEntity codeRepositorySettings;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ims_settings_id")
    @Setter
    private ImsSettingsEntity imsSettings;

}
