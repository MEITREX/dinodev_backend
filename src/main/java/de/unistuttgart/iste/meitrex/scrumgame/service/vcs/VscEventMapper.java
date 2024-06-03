package de.unistuttgart.iste.meitrex.scrumgame.service.vcs;

import com.fasterxml.jackson.databind.JsonNode;
import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;

import java.util.*;

@FunctionalInterface
public interface VscEventMapper {

    /**
     * Maps a JSON node received from a VCS web hook to a list of CreateEventInput objects.
     *
     * @param jsonNode payload of the VCS web hook
     * @param headers  headers of the VCS web hook request
     * @return the mapped list of CreateEventInput objects
     */
    List<CreateEventInput> mapToCreateEventInputList(JsonNode jsonNode, Map<String, String> headers);
}
