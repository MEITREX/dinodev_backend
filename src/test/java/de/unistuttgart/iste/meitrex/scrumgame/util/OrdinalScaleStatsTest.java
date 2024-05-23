package de.unistuttgart.iste.meitrex.scrumgame.util;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrdinalScaleStatsTest {

    @Test
    public void test_empty_list() {
        // Arrange
        List<Integer> data = Collections.emptyList();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> OrdinalScaleStats.calculate(data));
    }

    @Test
    public void test_empty_map() {
        // Arrange
        Map<String, Long> countMap = Collections.emptyMap();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> OrdinalScaleStats.calculate(countMap));
    }

    @Test
    public void test_calculateStatisticsForListWithDuplicates() {
        // Arrange
        List<Integer> data = Arrays.asList(8, 2, 3, 2, 4, 5, 4, 6, 7, 7, 1);

        // Act
        OrdinalScaleStats<Integer> stats = OrdinalScaleStats.calculate(data);

        // Assert
        assertEquals(1, stats.getMin());
        assertEquals(8, stats.getMax());
        assertEquals(4, stats.getMedian());
        assertEquals(2, stats.getMode());
    }

    @Test
    public void test_calculateStatisticsForMapWithDuplicates() {
        // Arrange
        Map<String, Long> countMap = new HashMap<>();
        countMap.put("A", 3L);
        countMap.put("B", 2L);
        countMap.put("C", 4L);
        countMap.put("D", 1L);

        // Act
        OrdinalScaleStats<String> stats = OrdinalScaleStats.calculate(countMap);

        // Assert
        assertEquals("A", stats.getMin());
        assertEquals("D", stats.getMax());
        assertEquals("C", stats.getMedian());
        assertEquals("C", stats.getMode());
    }
}
