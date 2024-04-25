package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AudienceValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
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

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    // URI to validate the JWT tokens. Defined in the application.properties file.
    @Value("${meitrex.auth.issuer-uri}")
    private String issuerUri;

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
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuerUri);

        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, new AudienceValidator());

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

}
