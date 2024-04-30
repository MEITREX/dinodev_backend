package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides a bean for the ModelMapper. The model mapper is used to map entities to DTOs and vice versa.
 */
@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        // Disable collections merge, instead collections are replaced
        modelMapper.getConfiguration()
                .setCollectionsMergeEnabled(false)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper
                .registerModule(new GlobalUserMapping())
                .registerModule(new GlobalUserRoleMapping())
                .registerModule(new UserInProjectMapping())
                .registerModule(new ProjectRoleMapping())
                .registerModule(new ProjectMapping())
                .registerModule(new MeetingMapping());

        return modelMapper;
    }
}
