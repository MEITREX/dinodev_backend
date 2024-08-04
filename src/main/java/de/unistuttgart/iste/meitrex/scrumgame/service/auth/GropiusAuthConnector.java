package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import de.unistuttgart.iste.gropius.generated.dto.CurrentUserQueryRequest;
import de.unistuttgart.iste.gropius.generated.dto.GropiusGropiusUser;
import de.unistuttgart.iste.gropius.generated.dto.GropiusUserResponseProjection;
import de.unistuttgart.iste.meitrex.common.graphqlclient.GraphQlRequestExecutor;
import de.unistuttgart.iste.meitrex.generated.dto.BasicUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@RequiredArgsConstructor
public class GropiusAuthConnector implements AuthConnector {

    private final GraphQlRequestExecutor graphQlRequestExecutor;
    private final String publicKey;

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
        var publicKeyPem = new String(Base64.getDecoder().decode(publicKey))
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("\n", "")
                .replace("-----END PUBLIC KEY-----", "");
        var encoded = Base64.getDecoder().decode(publicKeyPem);
        RSAPublicKey rsaPublicKey = null;
        try {
            rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(encoded));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("Wrong setup of environment variables");
        }
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }

    private OAuth2TokenValidator<Jwt> createAudienceValidator() {
        return new DelegatingOAuth2TokenValidator<>(JwtValidators.createDefault(), new AudienceValidator("backend"));
    }
}
