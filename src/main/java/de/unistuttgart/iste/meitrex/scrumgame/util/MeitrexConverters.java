package de.unistuttgart.iste.meitrex.scrumgame.util;

import lombok.NoArgsConstructor;
import org.modelmapper.Converter;

import java.util.*;
import java.util.function.*;

/**
 * Converters for the model mapper.
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MeitrexConverters {

    /**
     * Converts a collection to its size.
     */
    public static <T> Converter<Collection<T>, Integer> collectionToSize() {
        return context -> context.getSource().size();
    }

    /**
     * Converts each element of a collection.
     *
     * @param elementConverter the converter for the elements
     * @return the converter
     */
    public static <I, O> ListConverter<I, O> listConverter(Function<I, O> elementConverter) {
        return new ListConverter<>(elementConverter);
    }
}
