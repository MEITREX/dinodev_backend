package de.unistuttgart.iste.meitrex.scrumgame.controller.crs;

import com.fasterxml.jackson.databind.JsonNode;
import de.unistuttgart.iste.meitrex.scrumgame.service.crs.CrsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final CrsService vcsService;

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestBody JsonNode body,
            @RequestHeader Map<String, String> headers,
            @RequestParam UUID projectId
    ) {
        vcsService.handleWebhook(body, headers, projectId);
    }

}
