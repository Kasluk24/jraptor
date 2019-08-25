package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.TripBuilder;
import org.junit.jupiter.api.Test;

import static com.raoulvdberge.raptor.RaptorTestUtils.assertRaptorResult;
import static com.raoulvdberge.raptor.model.TripBuilderTestUtils.*;

class RaptorTest {
    @Test
    void testFindingJourneyWithDirectConnection() {
        var sut = new Raptor(new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .build()
        );

        var result = sut.plan("A", "C", time(10, 0));

        assertRaptorResult("[[A -> C]]", result);
    }

    @Test
    void testFindingJourneyWithSingleConnection() {
        var sut = new Raptor(new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 0))
                .stop("B", time(10, 3), time(10, 8))
                .stop("E", time(10, 10), nullTime()))
            .build()
        );

        var result = sut.plan("A", "E", time(10, 0));

        assertRaptorResult("[[A -> B -> E]]", result);
    }

    @Test
    void testNotFindingJourneyThatCannotBeMade() {
        var sut = new Raptor(new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 0))
                .stop("B", time(10, 1), time(10, 2))
                .stop("E", time(10, 10), nullTime()))
            .build()
        );

        var result = sut.plan("A", "E", time(10, 0));

        assertRaptorResult("[]", result);
    }

    @Test
    void testFindingFastestAndLeastChanges() {
        var sut = new Raptor(new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("B", nullTime(), time(10, 6))
                .stop("C", time(10, 7), nullTime()))
            .build()
        );

        var result = sut.plan("A", "C", time(10, 0));

        assertRaptorResult("[[A -> C], [A -> B -> C]]", result);
    }

    @Test
    void testFindingFastestJourneyWhereAmountOfChangesIsTheSame() {
        var sut = new Raptor(new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 2), time(10, 2))
                .stop("C", time(10, 5), nullTime()))
            .trip(t -> t
                .stop("C", nullTime(), time(10, 6))
                .stop("D", time(10, 7), time(10, 7))
                .stop("E", time(10, 8), nullTime()))
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("F", time(10, 2), time(10, 2))
                .stop("G", time(10, 5), nullTime()))
            .trip(t -> t
                .stop("G", nullTime(), time(10, 6))
                .stop("H", time(10, 6), time(10, 6))
                .stop("E", time(10, 7), nullTime()))
            .build()
        );

        var result = sut.plan("A", "E", time(10, 0));

        assertRaptorResult("[[A -> G -> E]]", result);
    }
}
