package de.unistuttgart.iste.meitrex.scrumgame.controller.vcs;

import com.fasterxml.jackson.databind.JsonNode;
import de.unistuttgart.iste.meitrex.scrumgame.service.vcs.VcsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final VcsService vcsService;

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestBody JsonNode body,
            @RequestHeader Map<String, String> headers
    ) {
        log.info("Received webhook with headers: {}", headers);
        vcsService.handleWebhook(body, headers);
    }

}
