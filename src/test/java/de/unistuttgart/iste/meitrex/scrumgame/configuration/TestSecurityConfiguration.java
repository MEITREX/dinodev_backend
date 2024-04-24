package de.unistuttgart.iste.meitrex.scrumgame.configuration;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Profile("test")
@Configuration
public class TestSecurityConfiguration {

    /**
     * Configures the security for the development environment.
     */
    @Bean
    @Profile("test")
    DefaultSecurityFilterChain springDevWebFilterChain(HttpSecurity http) throws Exception {
        return http
                // disable CSRF for the GraphQL endpoint
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/graphql**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                // disable CORS
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
                .build();
    }
}