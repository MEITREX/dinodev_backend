package de.unistuttgart.iste.meitrex.scrumgame.mapping;

import de.unistuttgart.iste.meitrex.scrumgame.config.ModelMapperConfiguration;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Tests that all model mapper mappings are valid.
 */
class ModelMapperValidTest {

    @Test
    void testModelMapperValid() {
        ModelMapper modelMapper = new ModelMapperConfiguration().modelMapper();

        assertDoesNotThrow(modelMapper::validate);
    }

}
