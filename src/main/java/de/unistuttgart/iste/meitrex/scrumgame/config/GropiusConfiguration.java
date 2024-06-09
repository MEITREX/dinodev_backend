package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.common.graphqlclient.GraphQlRequestExecutor;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthConnector;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthTokenFromHeaderSupplier;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius.GropiusAuthConnector;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.WebGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.function.*;

@Configuration
public class GropiusConfiguration {

    private static final int MAX_IN_MEMORY_SIZE = 16 * 1024 * 1024;

    @Bean
    public Supplier<String> tokenSupplier() {
        return new AuthTokenFromHeaderSupplier();
    }

    @Value("${gropius.url}")
    private String gropiusUrl;

    @Bean
    public HttpGraphQlClient baseGraphQlClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(gropiusUrl + "/graphql")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE))
                .build();

        return HttpGraphQlClient.builder(webClient).build();
    }

    @Bean
    public Supplier<WebGraphQlClient> gropiusGraphQlClientWithAuthTokenSupplier() {
        return () -> baseGraphQlClient()
                .mutate()
                .headers(headers -> headers.setBearerAuth(tokenSupplier().get()))
                .build();
    }

    @Bean
    public GraphQlRequestExecutor graphQlRequestExecutor(
            Supplier<WebGraphQlClient> gropiusGraphQlClientWithAuthTokenSupplier) {
        return new GraphQlRequestExecutor(gropiusGraphQlClientWithAuthTokenSupplier);
    }

    // Secret to validate the JWT tokens. Defined in the application.properties file.
    @Value("${gropius.auth.secret:#{null}}")
    @Nullable
    private String secret;

    @Bean
    public AuthConnector authConnector(GraphQlRequestExecutor graphQlRequestExecutor) {
        Objects.requireNonNull(secret);
        return new GropiusAuthConnector(graphQlRequestExecutor, secret);
    }
}
