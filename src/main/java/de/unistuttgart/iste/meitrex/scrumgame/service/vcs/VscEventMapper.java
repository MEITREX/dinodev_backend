package de.unistuttgart.iste.meitrex.scrumgame.service.vcs;

import com.fasterxml.jackson.databind.JsonNode;
import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;

import java.util.*;

@FunctionalInterface
public interface VscEventMapper {

    /**
     * Maps a JSON node received from a VCS web hook to a CreateEventInput object.
     *
     * @param jsonNode payload of the VCS web hook
     * @param headers  headers of the VCS web hook request
     * @return the mapped CreateEventInput object
     */
    Optional<CreateEventInput> mapToCreateEventInput(JsonNode jsonNode, Map<String, String> headers);

}
