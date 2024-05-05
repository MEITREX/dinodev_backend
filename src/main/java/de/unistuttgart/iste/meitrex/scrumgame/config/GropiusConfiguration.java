package de.unistuttgart.iste.meitrex.scrumgame.config;

import de.unistuttgart.iste.meitrex.common.graphqlclient.GraphQlRequestExecutor;
import de.unistuttgart.iste.meitrex.scrumgame.ims.ImsConnector;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.ImsUtilityConnector;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius.GropiusConnector;
import de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius.GropiusUtilityConnector;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.WebGraphQlClient;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Supplier;

@Configuration
public class GropiusConfiguration {

    @Bean
    public Supplier<String> tokenSupplier() {
        return () -> {
            ServletRequestAttributes attributes
                    = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes == null) {
                throw new IllegalStateException("No current ServletRequestAttributes");
            }

            HttpServletRequest request = attributes.getRequest();
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null) {
                throw new AccessDeniedException("No Authorization header found in the request");
            }

            if (authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            }

            return authHeader;
        };
    }

    @Value("${gropius.url}")
    private String gropiusUrl;

    @Bean
    public HttpGraphQlClient baseGraphQlClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(gropiusUrl + "/graphql")
                .build();

        return HttpGraphQlClient.builder(webClient).build();
    }

    @Bean
    public Supplier<WebGraphQlClient> graphQlClientWithAuthTokenSupplier() {
        return () -> baseGraphQlClient()
                .mutate()
                .headers(headers -> headers.setBearerAuth(tokenSupplier().get()))
                .build();
    }

    @Bean
    public GraphQlRequestExecutor graphQlRequestExecutor(Supplier<WebGraphQlClient> graphQlClientWithAuthTokenSupplier) {
        return new GraphQlRequestExecutor(graphQlClientWithAuthTokenSupplier);
    }

    @Bean
    public ImsConnector imsConnector(GraphQlRequestExecutor graphQlRequestExecutor) {
        return new GropiusConnector(graphQlRequestExecutor);
    }

    @Bean
    public ImsUtilityConnector imsUtilityConnector(GraphQlRequestExecutor graphQlRequestExecutor) {
        return new GropiusUtilityConnector(graphQlRequestExecutor);
    }
}
