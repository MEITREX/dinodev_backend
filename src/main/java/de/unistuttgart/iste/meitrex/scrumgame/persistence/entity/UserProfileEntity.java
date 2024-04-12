package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

}
