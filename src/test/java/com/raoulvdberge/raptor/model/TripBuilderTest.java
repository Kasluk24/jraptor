package com.raoulvdberge.raptor.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.raoulvdberge.raptor.model.TripBuilderTestUtils.nullTime;
import static com.raoulvdberge.raptor.model.TripBuilderTestUtils.time;

class TripBuilderTest {
    @Test
    void testAddingSingleTrip() {
        var trips = new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .build();

        Assertions.assertEquals(trips.size(), 1);

        var stopTimes = trips.get(0).getStopTimes();

        Assertions.assertEquals(stopTimes.size(), 3);
        Assertions.assertEquals(stopTimes.get(0).getStop().getName(), "A");
        Assertions.assertEquals(stopTimes.get(1).getStop().getName(), "B");
        Assertions.assertEquals(stopTimes.get(2).getStop().getName(), "C");
    }

    @Test
    void testAddingMultipleTrips() {
        var trips = new TripBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 0))
                .stop("E", time(10, 5), time(10, 6))
                .stop("F", time(10, 10), nullTime()))
            .build();

        Assertions.assertEquals(trips.size(), 2);

        var stopTimes = trips.get(0).getStopTimes();

        Assertions.assertEquals(stopTimes.size(), 3);
        Assertions.assertEquals(stopTimes.get(0).getStop().getName(), "A");
        Assertions.assertEquals(stopTimes.get(1).getStop().getName(), "B");
        Assertions.assertEquals(stopTimes.get(2).getStop().getName(), "C");

        stopTimes = trips.get(1).getStopTimes();

        Assertions.assertEquals(stopTimes.size(), 3);
        Assertions.assertEquals(stopTimes.get(0).getStop().getName(), "D");
        Assertions.assertEquals(stopTimes.get(1).getStop().getName(), "E");
        Assertions.assertEquals(stopTimes.get(2).getStop().getName(), "F");
    }

    @Test
    void testStopsRequired() {
        var thrown = Assertions.assertThrows(TripBuilderException.class, () -> {
            new TripBuilder()
                .trip(t -> {
                })
                .build();
        });

        Assertions.assertEquals(thrown.getMessage(), "Trip has no stops");
    }

    @Test
    void testFirstStopNeedsToArriveAtNull() {
        var thrown = Assertions.assertThrows(TripBuilderException.class, () -> {
            new TripBuilder()
                .trip(t -> t
                    .stop("A", time(10, 0), time(10, 0))
                    .stop("B", time(10, 5), time(10, 6))
                    .stop("C", time(10, 10), nullTime()))
                .build();
        });

        Assertions.assertEquals(thrown.getMessage(), "The first stop needs to arrive at 0");
    }

    @Test
    void testIntermediateStopNeedsToNotArriveAtNull() {
        var thrown = Assertions.assertThrows(TripBuilderException.class, () -> {
            new TripBuilder()
                .trip(t -> t
                    .stop("A", nullTime(), time(10, 0))
                    .stop("B", nullTime(), time(10, 6))
                    .stop("C", time(10, 10), nullTime()))
                .build();
        });

        Assertions.assertEquals(thrown.getMessage(), "Already added the first stop, we can't arrive at 0 again");
    }

    @Test
    void testFirstStopNeedsToNotDepartAtNull() {
        var thrown = Assertions.assertThrows(TripBuilderException.class, () -> {
            new TripBuilder()
                .trip(t -> t
                    .stop("A", nullTime(), nullTime())
                    .stop("B", time(10, 5), time(10, 6))
                    .stop("C", time(10, 10), nullTime()))
                .build();
        });

        Assertions.assertEquals(thrown.getMessage(), "The first stop can't depart at 0");
    }

    @Test
    void testLastStopNeedsToDepartAtNull() {
        var thrown = Assertions.assertThrows(TripBuilderException.class, () -> {
            new TripBuilder()
                .trip(t -> t
                    .stop("A", nullTime(), time(10, 0))
                    .stop("B", time(10, 5), time(10, 6))
                    .stop("C", time(10, 10), time(10, 11)))
                .build();
        });

        Assertions.assertEquals(thrown.getMessage(), "The last stop needs to depart at 0");
    }

    @Test
    void testCannotAddStopsAfterStopWithDepartureAsNull() {
        var thrown = Assertions.assertThrows(TripBuilderException.class, () -> {
            new TripBuilder()
                .trip(t -> t
                    .stop("A", nullTime(), time(10, 0))
                    .stop("B", time(10, 5), time(10, 6))
                    .stop("C", time(10, 10), nullTime())
                    .stop("D", time(10, 10), nullTime()))
                .build();
        });

        Assertions.assertEquals(thrown.getMessage(), "Already added the last stop, can't add another one");
    }
}
