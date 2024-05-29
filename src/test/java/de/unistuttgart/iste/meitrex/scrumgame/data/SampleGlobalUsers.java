package de.unistuttgart.iste.meitrex.scrumgame.data;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.user.GlobalUserEntity;

import java.util.*;

public class SampleGlobalUsers {

    private SampleGlobalUsers() {
    }

    public static GlobalUserEntity.GlobalUserEntityBuilder sampleGlobalUserEntityBuilder() {
        return GlobalUserEntity.builder()
                .id(UUID.randomUUID())
                .username("user")
                .roles(new ArrayList<>())
                .vcsUserId("vcsUserId")
                .avatar("avatar");
    }

}
