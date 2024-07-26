package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.scrumgame.crs.github.GitHubAdapter;
import de.unistuttgart.iste.meitrex.scrumgame.external.ExternalSystemAdapter;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.GlobalUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.function.*;

@Configuration
public class CrsConfig {

    @Bean
    public ExternalSystemAdapter crsAdapter(GlobalUserService globalUserService) {
        Function<String, Optional<UUID>> userIdFinder = globalUserService::findUserByGithubId;
        return new GitHubAdapter(userIdFinder);
    }
}

