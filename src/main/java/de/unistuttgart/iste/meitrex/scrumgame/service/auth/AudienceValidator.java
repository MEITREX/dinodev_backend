package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes;

import java.util.List;

@Slf4j
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
    private final OAuth2Error error = new BearerTokenError(BearerTokenErrorCodes.INVALID_TOKEN,
            HttpStatus.UNAUTHORIZED,
            "The required audience is missing",
            "https://tools.ietf.org/html/rfc6750#section-3.1");

    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        List<String> audiences = jwt.getAudience();
        log.info("Audiences: {}", audiences);

        if (audiences.contains("account")) {
            return OAuth2TokenValidatorResult.success();
        }
        return OAuth2TokenValidatorResult.failure(error);
    }
}