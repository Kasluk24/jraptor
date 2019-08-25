package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Stop;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

public class RaptorTestUtils {
    private static String resultsToString(List<List<Stop>> results) {
        return "[" + results
            .stream()
            .map(stops -> "[" + stops.stream().map(Stop::getName).collect(Collectors.joining(" -> ")) + "]")
            .collect(Collectors.joining(", ")) + "]";
    }

    public static void assertRaptorResult(String expected, List<List<Stop>> actual) {
        Assertions.assertEquals(expected, resultsToString(actual));
    }
}
