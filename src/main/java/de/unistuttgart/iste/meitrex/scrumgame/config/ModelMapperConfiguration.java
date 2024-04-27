package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides a bean for the ModelMapper. The model mapper is used to map entities to DTOs and vice versa.
 */
@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper()
                .registerModule(new GlobalUserMapping())
                .registerModule(new UserInProjectMapping());
                .registerModule(new GlobalUserRoleMapping())
                .registerModule(new ProjectMapping())
    }
}
