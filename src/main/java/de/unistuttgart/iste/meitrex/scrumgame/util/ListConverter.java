package de.unistuttgart.iste.meitrex.scrumgame.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.*;
import java.util.function.*;

/**
 * Converter for model mapper to convert a list of elements from one type to another.
 */
@RequiredArgsConstructor
public class ListConverter<I, O> implements Converter<List<I>, List<O>> {

    private final Function<I, O> elementConverter;

    @SuppressWarnings("java:S1168")
    // for the graphql framework it makes more sense to return null instead of an empty list
    @Override
    public List<O> convert(MappingContext context) {
        @SuppressWarnings("unchecked") Collection<I> source
                = (Collection<I>) context.getSource();
        if (source == null) {
            return null;
        }
        return source.stream().map(elementConverter).toList();
    }

}
