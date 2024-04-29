package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AudienceValidator;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    // URI to validate the JWT tokens. Defined in the application.properties file.
    @Value("${meitrex.auth.issuer-uri:#{null}}")
    @Nullable
    private String issuerUri;

    // Secret to validate the JWT tokens. Defined in the application.properties file.
    // Used if the issuer URI is not defined.
    @Value("${meitrex.auth.secret:#{null}}")
    @Nullable
    private String secret;

    /**
     * Configures the security for the development environment.
     */
    @Bean
    @Profile("dev")
    DefaultSecurityFilterChain springDevWebFilterChain(HttpSecurity http) throws Exception {
        return http
                // disable CSRF for the GraphQL endpoint
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/graphql**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                // disable CORS
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/graphql**").permitAll()
                        // allow access to the GraphiQL interface
                        .requestMatchers("/graphiql**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                .build();
    }

    /**
     * Configures the security for the production environment.
     */
    @Bean
    @Profile("prod")
    DefaultSecurityFilterChain prodSpringWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests(authz -> authz
                        // allow OPTIONS requests to the GraphQL endpoint
                        .requestMatchers(HttpMethod.OPTIONS, "/graphql**").permitAll()
                        .anyRequest().authenticated()) // All requests require authentication
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                .build();
    }

    @Bean
    @Profile({"prod", "dev"})
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = initNimbusDecoder();
        OAuth2TokenValidator<Jwt> withAudience = createAudienceValidator();

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    private OAuth2TokenValidator<Jwt> createAudienceValidator() {
        if (issuerUri != null) {
            OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
            return new DelegatingOAuth2TokenValidator<>(withIssuer, new AudienceValidator());
        }

        return new DelegatingOAuth2TokenValidator<>(JwtValidators.createDefault(),
                new AudienceValidator());
    }

    private NimbusJwtDecoder initNimbusDecoder() {
        if (issuerUri != null) {
            return JwtDecoders.fromOidcIssuerLocation(issuerUri);
        } else {
            SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(secret), "HmacSHA256");
            return NimbusJwtDecoder.withSecretKey(secretKey).build();
        }
    }

}
