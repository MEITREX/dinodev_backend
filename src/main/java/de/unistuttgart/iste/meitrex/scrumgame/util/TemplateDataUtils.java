package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.TemplateField;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TemplateDataUtils {

    public static Optional<? extends TemplateField> findField(Event event, String key) {
        return event.getEventData().stream()
                .filter(field -> field.getKey().equals(key))
                .findFirst();
    }

    public static Optional<String> findStringField(Event event, String key) {
        return findField(event, key).map(TemplateField::getValue);
    }

    public static Optional<Integer> findIntField(Event event, String key) {
        try {
            return findField(event, key)
                    .map(TemplateField::getValue)
                    .map(Integer::parseInt);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}
