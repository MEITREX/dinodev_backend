package de.unistuttgart.iste.meitrex.scrumgame.service.vcs;

import com.fasterxml.jackson.databind.JsonNode;
import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.rulesengine.util.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class VcsService {

    private final EventPublisher<Event, CreateEventInput> eventPublisher;
    private final VscEventMapper                          vscEventMapper;

    public void handleWebhook(JsonNode jsonNode, Map<String, String> headers) {
        List<CreateEventInput> inputs = vscEventMapper.mapToCreateEventInputList(jsonNode, headers);
        inputs.forEach(eventPublisher::publishEvent);
    }

}
