package de.unistuttgart.iste.meitrex.scrumgame.service.crs;

import com.fasterxml.jackson.databind.JsonNode;
import de.unistuttgart.iste.meitrex.generated.dto.CreateEventInput;
import de.unistuttgart.iste.meitrex.rulesengine.util.DefaultEventPublisher;
import de.unistuttgart.iste.meitrex.scrumgame.external.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CrsService {

    private final DefaultEventPublisher eventPublisher;
    private final ExternalSystemAdapter crsAdapter;

    public void handleWebhook(JsonNode jsonNode, Map<String, String> headers, UUID projectId) {
        List<CreateEventInput> inputs = crsAdapter.mapToDinoDevEvents(jsonNode, headers, projectId);
        inputs.forEach(eventPublisher::publishEvent);
    }

}
