package de.unistuttgart.iste.meitrex.scrumgame.util;

import lombok.Getter;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * A utility class for calculating statistics on ordinal data. This class provides functionality to calculate minimum,
 * maximum, median, and mode for a given set of data that implements the Comparable interface.
 *
 * @param <T> the type of the elements in the data set, which must extend Comparable
 */
@Getter
public class OrdinalScaleStats<T extends Comparable<T>> {

    private final List<T>      data;
    private final Map<T, Long> counts;

    private T min;
    private T max;
    private T median;
    private T mode;

    private OrdinalScaleStats(List<T> data) {
        this.data = new ArrayList<>(data);
        this.counts = this.data.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        doCalculations();
    }

    private OrdinalScaleStats(Map<T, Long> countMap) {
        this.data = new ArrayList<>(countMap.entrySet().stream()
                .flatMap(entry -> Stream.generate(entry::getKey).limit(entry.getValue()))
                .toList());
        this.counts = countMap;

        doCalculations();
    }

    private void doCalculations() {
        if (this.data.isEmpty()) {
            throw new IllegalArgumentException("Data must not be empty");
        }

        Collections.sort(this.data);
        this.min = this.data.getFirst();
        this.max = this.data.getLast();

        // remark: in case of an even number of elements, the median is the average of the two middle elements
        // but since the elements are of type T, we cannot calculate the average
        // and taking the element at the middle index works for our use case
        this.median = this.data.get(this.data.size() / 2);
        this.mode = calculateMode();
    }

    private T calculateMode() {
        return counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    /**
     * Calculate statistics for a given list of elements. The list must not be empty.
     *
     * @param data the list of elements
     * @param <T>  the type of the elements in the data set, which must extend Comparable
     * @return an OrdinalScaleStats object containing the calculated statistics
     */
    public static <T extends Comparable<T>> OrdinalScaleStats<T> calculate(List<T> data) {
        return new OrdinalScaleStats<>(data);
    }

    /**
     * Calculate statistics for a given map of counts of elements. The map must not be empty.
     *
     * @param <T>      the type of the elements in the data set
     * @param countMap a map containing the counts of each element
     * @return an OrdinalScaleStats object containing the calculated statistics
     */
    public static <T extends Comparable<T>> OrdinalScaleStats<T> calculate(Map<T, Long> countMap) {
        return new OrdinalScaleStats<>(countMap);
    }
}
