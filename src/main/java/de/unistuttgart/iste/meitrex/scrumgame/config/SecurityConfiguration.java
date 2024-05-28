package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AudienceValidator;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthConnector;
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
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.*;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {


    @Value("${meitrex.frontend.url}")
    private String frontendUrl;

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    /**
     * Configures the security for the development environment.
     */
    @Bean
    @Profile("dev")
    DefaultSecurityFilterChain springDevWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/graphql**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                // disable CORS
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        // allow access to the GraphQL endpoint (required for schema introspection)
                        .requestMatchers( "/graphql**").permitAll()
                        // for now: allow access to the WebSocket endpoint
                        .requestMatchers("/graphql-ws**").permitAll()
                        // allow access to the GraphiQL interface
                        .requestMatchers("/graphiql**").permitAll()
                        .anyRequest().permitAll())
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
                .cors(cors -> cors.disable())
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

    @Value("${gropius.auth.secret}")
    private String secret;

    private OAuth2TokenValidator<Jwt> createAudienceValidator() {
        return new DelegatingOAuth2TokenValidator<>(JwtValidators.createDefault(),
                new AudienceValidator("backend"));
    }

    private NimbusJwtDecoder initNimbusDecoder() {
            SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(secret), "HmacSHA256");
            return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

}
