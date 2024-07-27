package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.*;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String GRAPHQL_ROUTE    = "/graphql**";
    private static final String GRAPHQL_WS_ROUTE = "/graphql-ws**";
    private static final String WEBHOOK_ROUTE    = "/webhook**";
    private static final String GRAPHIQL_ROUTE   = "/graphiql**";

    @Bean
    @Profile("dev")
    @SuppressWarnings({"java:S5122"}) // only dev profile, no security issue
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "accept",
                "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Configures the security for the development environment.
     */
    @Bean
    @Profile("dev")
    @SuppressWarnings({"java:S4502", "java:S3330"})
    // only dev profile, no security issue
    DefaultSecurityFilterChain springDevWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(GRAPHQL_ROUTE)
                        .ignoringRequestMatchers(WEBHOOK_ROUTE)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authz -> authz
                        // allow access to the GraphQL endpoint (required for schema introspection)
                        .requestMatchers(GRAPHQL_ROUTE).permitAll()
                        // for now: allow access to the WebSocket endpoint
                        .requestMatchers(GRAPHQL_WS_ROUTE).permitAll()
                        // allow access to the GraphiQL interface
                        .requestMatchers(GRAPHIQL_ROUTE).permitAll()
                        // allow access to webhooks
                        .requestMatchers(HttpMethod.POST, WEBHOOK_ROUTE).permitAll()
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
    @SuppressWarnings({"java:S3330", "java:S4502"})
    // currently we use a rudimentary CSRF protection, also webhook access needs to be allowed
    DefaultSecurityFilterChain prodSpringWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(WEBHOOK_ROUTE) // allow access to webhooks
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        // allow OPTIONS requests to the GraphQL endpoint
                        .requestMatchers(HttpMethod.OPTIONS, GRAPHQL_ROUTE).permitAll()
                        // for now: allow access to the WebSocket endpoint
                        .requestMatchers(GRAPHQL_WS_ROUTE).permitAll()
                        // allow access to the GraphiQL interface
                        .requestMatchers(GRAPHIQL_ROUTE).permitAll()
                        // allow access to webhooks
                        .requestMatchers(HttpMethod.POST, WEBHOOK_ROUTE).permitAll()
                        .anyRequest().authenticated()) // All requests require authentication
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                .build();
    }

    @Bean
    @Profile({"prod", "dev"})
    public JwtDecoder jwtDecoder(AuthConnector authConnector) {
        return authConnector.getJwtDecoder();
    }

}
