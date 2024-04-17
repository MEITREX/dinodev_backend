package de.unistuttgart.iste.meitrex.scrumgame.service;

import de.unistuttgart.iste.meitrex.generated.dto.GlobalPrivilege;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.role.GlobalUserRoleEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.GlobalUserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DefaultDataInitializer {

    private final GlobalUserRepository globalUserRepository;
    private final GlobalUserRoleRepository globalUserRoleRepository;

    @Value("${meitrex.auth.default-admin-user-id}")
    private String defaultAdminUserId;

    @PostConstruct
    public void init() {
        createOrUpdateDefaultAdminUser();
    }

    private void createOrUpdateDefaultAdminUser() {
        GlobalUserRoleEntity adminRole = ensureAdminRoleExists();

        UUID userId = UUID.fromString(defaultAdminUserId);
        GlobalUserEntity adminUser = globalUserRepository.findById(userId)
                .orElseGet(() -> GlobalUserEntity.builder()
                        .id(userId)
                        .name("admin")
                        .build());

        adminUser.getRoles().add(adminRole);

        globalUserRepository.save(adminUser);
    }


    private GlobalUserRoleEntity ensureAdminRoleExists() {
        return globalUserRoleRepository.save(GlobalUserRoleEntity.builder()
                .name("ADMIN")
                // add all global privileges to the admin role
                .globalPrivileges(Arrays.asList(GlobalPrivilege.values()))
                .build());
    }

}
