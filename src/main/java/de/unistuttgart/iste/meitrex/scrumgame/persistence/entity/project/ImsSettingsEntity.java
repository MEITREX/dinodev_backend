package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ims_settings")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImsSettingsEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "ims_name")
    @Setter
    private String imsName;

    @Column(name = "ims_project_id")
    @Setter
    private String imsProjectId;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn(name = "position")
    @Setter
    @Builder.Default
    private List<IssueStateEmbeddable> issueStates = new ArrayList<>();

}
