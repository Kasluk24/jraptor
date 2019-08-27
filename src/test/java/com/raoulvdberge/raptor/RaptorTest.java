package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.TripBuilder;
import org.junit.jupiter.api.Test;

import static com.raoulvdberge.raptor.model.TripBuilderTestUtils.nullTime;
import static com.raoulvdberge.raptor.model.TripBuilderTestUtils.time;

class RaptorTest {
    @Test
    void testFindingJourneyWithDirectConnection() {
        var sut = new InMemoryRaptorFactory(new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .build()
        ).create();

        var result = sut.plan("A", "C", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(1)
                .assertLegTimetable("A", time(10, 0), "C", time(10, 10)));
    }

    @Test
    void testFindingJourneyWithSingleConnection() {
        var sut = new InMemoryRaptorFactory(new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 0))
                .stop("B", time(10, 3), time(10, 8))
                .stop("E", time(10, 10), nullTime()))
            .build()
        ).create();

        var result = sut.plan("A", "E", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(2)
                .assertLegTimetable("A", time(10, 0), "B", time(10, 5))
                .assertLegTimetable("B", time(10, 8), "E", time(10, 10)));
    }

    @Test
    void testNotFindingJourneyThatCannotBeMade() {
        var sut = new InMemoryRaptorFactory(new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 0))
                .stop("B", time(10, 1), time(10, 2))
                .stop("E", time(10, 10), nullTime()))
            .build()
        ).create();

        var result = sut.plan("A", "E", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(0);
    }

    @Test
    void testFindingFastestAndLeastChanges() {
        var sut = new InMemoryRaptorFactory(new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("B", nullTime(), time(10, 6))
                .stop("C", time(10, 7), nullTime()))
            .build()
        ).create();

        var result = sut.plan("A", "C", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(2)
            .journey(j -> j
                .assertLegCount(1)
                .assertLegTimetable("A", time(10, 0), "C", time(10, 10)))
            .journey(j -> j
                .assertLegCount(2)
                .assertLegTimetable("A", time(10, 0), "B", time(10, 5))
                .assertLegTimetable("B", time(10, 6), "C", time(10, 7)));
    }

    @Test
    void testFindingFastestJourneyWhereAmountOfChangesIsTheSame() {
        var sut = new InMemoryRaptorFactory(new TripBuilder()
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
        ).create();

        var result = sut.plan("A", "E", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(2)
                .assertLegTimetable("A", time(10, 0), "G", time(10, 5))
                .assertLegTimetable("G", time(10, 6), "E", time(10, 7)));
    }
}
