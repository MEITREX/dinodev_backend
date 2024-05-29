package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import de.unistuttgart.iste.meitrex.generated.dto.BasicUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import java.util.*;

/**
 * PROOF OF CONCEPT, NOT FULLY IMPLEMENTED
 * This auth connector is used to authenticate against an issuer, e.g. Keycloak.
 * It is used to authenticate the user and to validate the JWT token.
 * However, one need to find a way to provide auth tokens for the IMS, so just using this connector will not work.
 * Additionally, a way to get the user information is not implemented.
 */
@RequiredArgsConstructor
public class IssuerAuthConnector implements AuthConnector {

    private final String issuerUri;

    @Override
    public JwtDecoder getJwtDecoder() {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuerUri);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        var withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, new AudienceValidator("client"));

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Override
    public Optional<BasicUserInfo> getUser() {
        // not implemented
        return Optional.empty();
    }
}
