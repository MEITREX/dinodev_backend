package de.unistuttgart.iste.meitrex.scrumgame.util;

import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ScrumGameCollectionUtils {

    public static <T, K> Predicate<? super T> distinctByKey(Function<? super T, K> keyExtractor) {
        Set<K> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
