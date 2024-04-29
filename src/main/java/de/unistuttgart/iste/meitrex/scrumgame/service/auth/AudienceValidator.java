package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes;

import java.util.Arrays;
import java.util.List;

/**
 * Validates the audience of a JWT token.
 */
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    public static final String[] AUDIENCES = {"account", "backend", "profile"};

    private final OAuth2Error error = new BearerTokenError(BearerTokenErrorCodes.INVALID_TOKEN,
            HttpStatus.UNAUTHORIZED,
            "The required audience is missing",
            "https://tools.ietf.org/html/rfc6750#section-3.1");

    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        List<String> audiences = jwt.getAudience();

        if (Arrays.stream(AUDIENCES).anyMatch(audiences::contains)) {
            return OAuth2TokenValidatorResult.success();
        }
        return OAuth2TokenValidatorResult.failure(error);
    }
}