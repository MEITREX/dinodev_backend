package de.unistuttgart.iste.meitrex.scrumgame.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.function.*;

/**
 * Supplies the auth token from the Authorization header of the current request.
 */
@Component
@RequiredArgsConstructor
public class AuthTokenFromHeaderSupplier implements Supplier<String> {

    private static final String AUTH_HEADER   = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public String get() {
        String authHeader = getAuthHeader();

        if (authHeader.startsWith(BEARER_PREFIX)) {
            authHeader = authHeader.substring(BEARER_PREFIX.length());
        }

        // no need to check if the token is empty, as the JWT token validator will
        // validate the token and throw an exception if it is invalid

        return authHeader;
    }

    private static String getAuthHeader() {
        ServletRequestAttributes attributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            throw new IllegalStateException("No current ServletRequestAttributes");
        }

        HttpServletRequest request = attributes.getRequest();
        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader == null) {
            throw new AccessDeniedException("No Authorization header found in the request");
        }
        return authHeader;
    }
}
