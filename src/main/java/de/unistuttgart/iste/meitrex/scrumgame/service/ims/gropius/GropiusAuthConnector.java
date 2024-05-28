package de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius;

import de.unistuttgart.iste.gropius.generated.dto.CurrentUserQueryRequest;
import de.unistuttgart.iste.gropius.generated.dto.GropiusGropiusUser;
import de.unistuttgart.iste.gropius.generated.dto.GropiusUserResponseProjection;
import de.unistuttgart.iste.meitrex.common.graphqlclient.GraphQlRequestExecutor;
import de.unistuttgart.iste.meitrex.generated.dto.BasicUserInfo;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AudienceValidator;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

@RequiredArgsConstructor
public class GropiusAuthConnector implements AuthConnector {

    private final GraphQlRequestExecutor graphQlRequestExecutor;
    private final String secret;

    @Override
    public JwtDecoder getJwtDecoder() {
        NimbusJwtDecoder jwtDecoder = initNimbusDecoder();
        OAuth2TokenValidator<Jwt> withAudience = createAudienceValidator();

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Override
    public Optional<BasicUserInfo> getUser() {
        var request = new CurrentUserQueryRequest();

        var projection = new GropiusUserResponseProjection()
                .id()
                .avatar()
                .username()
                .isAdmin();

        return graphQlRequestExecutor
                .request(request)
                .projectTo(GropiusGropiusUser.class, projection)
                .retrieve()
                .map(gropiusUser -> BasicUserInfo.builder()
                        .setId(gropiusUser.getId())
                        .setIsAdmin(gropiusUser.getIsAdmin())
                        .setAvatar(gropiusUser.getAvatar())
                        .setUsername(gropiusUser.getUsername())
                        .build())
                .blockOptional();
    }

    private NimbusJwtDecoder initNimbusDecoder() {
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(secret), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

    private OAuth2TokenValidator<Jwt> createAudienceValidator() {
        return new DelegatingOAuth2TokenValidator<>(JwtValidators.createDefault(), new AudienceValidator("backend"));
    }
}
