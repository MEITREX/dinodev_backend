package de.unistuttgart.iste.meitrex.scrumgame.controller.ims;

import de.unistuttgart.iste.meitrex.generated.dto.BasicUserInfo;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthUtilityController {

    private final AuthConnector authConnector;

    @QueryMapping
    public BasicUserInfo currentUserInfo() {
        return authConnector.getUser().orElse(null);
    }
}
