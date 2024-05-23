package de.unistuttgart.iste.meitrex.scrumgame.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.*;
import java.util.function.*;

@RequiredArgsConstructor
public class ListConverter<I, O> implements Converter<List<I>, List<O>> {

    private final Function<I, O> elementConverter;

    @Override
    public List<O> convert(MappingContext context) {
        @SuppressWarnings("unchecked") List<I> source
                = (List<I>) context.getSource();
        if (source == null) {
            return null;
        }
        return source.stream().map(elementConverter).toList();
    }

}
