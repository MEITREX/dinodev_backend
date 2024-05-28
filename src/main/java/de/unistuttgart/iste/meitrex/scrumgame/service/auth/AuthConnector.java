package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import de.unistuttgart.iste.meitrex.generated.dto.BasicUserInfo;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.util.*;

/**
 * The auth connector is responsible for providing a JWT decoder and user information.
 */
public interface AuthConnector {

    /**
     * Get the JWT decoder.
     *
     * @return the JWT decoder
     */
    JwtDecoder getJwtDecoder();

    /**
     * Get the user information for the current user.
     *
     * @apiNote This method is only called on registration to pre-fill the user information.
     * @return the user information
     */
    Optional<BasicUserInfo> getUser();

}
