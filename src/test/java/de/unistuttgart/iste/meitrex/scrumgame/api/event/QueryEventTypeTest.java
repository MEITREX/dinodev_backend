package de.unistuttgart.iste.meitrex.scrumgame.api.event;

import de.unistuttgart.iste.meitrex.common.testutil.GraphQlApiTest;
import de.unistuttgart.iste.meitrex.generated.dto.DefaultEventType;
import de.unistuttgart.iste.meitrex.generated.dto.EventType;
import de.unistuttgart.iste.meitrex.rulesengine.EventTypeRegistry;
import de.unistuttgart.iste.meitrex.scrumgame.data.SampleEventTypes;
import de.unistuttgart.iste.meitrex.scrumgame.matchers.EventTypeMatcher;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.UserDefinedEventTypeRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static de.unistuttgart.iste.meitrex.common.testutil.MeitrexMatchers.each;
import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

@GraphQlApiTest
@ActiveProfiles("test")
class QueryEventTypeTest {

    @Autowired
    private UserDefinedEventTypeRepository userDefinedEventTypeRepository;

    @Autowired
    private EventTypeRegistry eventTypeRegistry;

    @Test
    void testGetEventTypes(GraphQlTester graphQlTester) {

        Collection<EventType> expectedEventTypes = new ArrayList<>();
        expectedEventTypes.addAll(eventTypeRegistry.getAll());
        expectedEventTypes.addAll(List.of(
                userDefinedEventTypeRepository.save(
                        SampleEventTypes.sampleUserDefinedEventTypeEntity().build()),
                userDefinedEventTypeRepository.save(
                        SampleEventTypes.sampleUserDefinedEventTypeEntity().build())
        ));

        String query = getQueryForAllEventTypes();

        List<DefaultEventType> result = graphQlTester.document(query)
                .execute()
                .path("eventTypes")
                .entityList(DefaultEventType.class)
                .get();

        assertThat(result, hasSize(expectedEventTypes.size()));
        assertThat(result, containsInAnyOrder(each(expectedEventTypes, EventTypeMatcher::matchingEventType)));
    }

    @Test
    void testGetSingleEventType(GraphQlTester graphQlTester) {
        EventType eventType = userDefinedEventTypeRepository.save(SampleEventTypes.sampleUserDefinedEventTypeEntity()
                .build());

        String query = getQueryForSingleEventType();

        DefaultEventType result = graphQlTester.document(query)
                .variable("id", eventType.getIdentifier())
                .execute()
                .path("eventType")
                .entity(DefaultEventType.class)
                .get();

        assertThat(result, EventTypeMatcher.matchingEventType(eventType));
    }

    private static @NotNull String getQueryForSingleEventType() {
        return gql("""
                query($id: String!) {
                    eventType(id: $id) {
                        identifier
                        description
                        defaultVisibility
                        eventSchema {
                            fields {
                                name
                                type
                                description
                                required
                                allowedValues
                            }
                        }
                        messageTemplate
                    }
                }
                """);
    }

    private static @NotNull String getQueryForAllEventTypes() {
        return gql("""
                query {
                    eventTypes {
                        identifier
                        description
                        defaultVisibility
                        eventSchema {
                            fields {
                                name
                                type
                                description
                                required
                                allowedValues
                            }
                        }
                        messageTemplate
                    }
                }
                """);
    }
}
