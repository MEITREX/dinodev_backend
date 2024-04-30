package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRoleId implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "project_id")
    private UUID projectId;
}
