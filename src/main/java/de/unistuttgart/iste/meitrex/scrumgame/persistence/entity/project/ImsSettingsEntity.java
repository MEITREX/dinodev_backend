package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ims_settings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImsSettingsEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "ims_name")
    private String imsName;
}
