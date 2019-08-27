package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Journey;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

class RaptorTestUtils {
    private static String resultsToString(List<Journey> results) {
        return "[" + results
            .stream()
            .map(j -> "[" + j.getLegs().stream().map(l -> l.getOrigin().getName() + " -> " + l.getDestination().getName()).collect(Collectors.joining(", ")) + "]")
            .collect(Collectors.joining(", ")) + "]";
    }

    static void assertRaptorResult(String expected, List<Journey> actual) {
        Assertions.assertEquals(expected, resultsToString(actual));
    }
}
