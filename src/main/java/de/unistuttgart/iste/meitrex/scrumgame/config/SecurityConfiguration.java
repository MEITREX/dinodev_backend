package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AudienceValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

    @Bean
    DefaultSecurityFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
        return http
                // Demonstrate that method security works
                // Best practice to use both for defense in depth
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/graphql**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                ) // todo only in dev mode
                .cors(cors -> cors.disable()) // todo only in dev mode
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/graphql**").permitAll()
                        .requestMatchers("/graphiql**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(withDefaults())
                //.httpBasic(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        String issuerUri = "http://localhost:9009/realms/GITS"; // replace with your issuer URI
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuerUri);

        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, new AudienceValidator());

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

}
