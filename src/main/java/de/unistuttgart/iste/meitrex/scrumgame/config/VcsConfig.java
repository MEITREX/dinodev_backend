package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.scrumgame.service.project.ProjectService;
import de.unistuttgart.iste.meitrex.scrumgame.service.user.GlobalUserService;
import de.unistuttgart.iste.meitrex.scrumgame.service.vcs.GitHubEventMapper;
import de.unistuttgart.iste.meitrex.scrumgame.service.vcs.VscEventMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.function.*;

@Configuration
public class VcsConfig {

    @Bean
    public VscEventMapper vscEventMapper(GlobalUserService globalUserService, ProjectService projectService) {
        Function<String, Optional<UUID>> userIdFinder = globalUserService::findUserByGithubId;
        return new GitHubEventMapper(userIdFinder, projectService);
    }
}

