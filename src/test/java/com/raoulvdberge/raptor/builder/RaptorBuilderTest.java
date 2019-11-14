package com.raoulvdberge.raptor.builder;

import com.raoulvdberge.raptor.model.impl.StopImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.raoulvdberge.raptor.util.TimeUtil.nullTime;
import static com.raoulvdberge.raptor.util.TimeUtil.time;

class RaptorBuilderTest {
    @Test
    void testAddingSingleTrip() {
        var trips = new RaptorBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .build()
            .getTrips();

        Assertions.assertEquals(trips.size(), 1);

        var stopTimes = trips.get(0).getStopTimes();

        Assertions.assertEquals(stopTimes.size(), 3);
        Assertions.assertEquals(stopTimes.get(0).getStop().getName(), "A");
        Assertions.assertEquals(stopTimes.get(1).getStop().getName(), "B");
        Assertions.assertEquals(stopTimes.get(2).getStop().getName(), "C");
    }

    @Test
    void testAddingMultipleTrips() {
        var trips = new RaptorBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 0))
                .stop("E", time(10, 5), time(10, 6))
                .stop("F", time(10, 10), nullTime()))
            .build()
            .getTrips();

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
    void testAddingSingleTransferWithTrips() {
        var transfers = new RaptorBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .transfer("A", "C", Duration.ofMinutes(1))
            .build()
            .getTransfers();

        Assertions.assertEquals(1, transfers.size());

        var transfersAtA = transfers.get(new StopImpl("A"));

        Assertions.assertEquals(transfersAtA.size(), 1);
        Assertions.assertEquals(transfersAtA.get(0).getOrigin().getName(), "A");
        Assertions.assertEquals(transfersAtA.get(0).getDestination().getName(), "C");
        Assertions.assertEquals(transfersAtA.get(0).getDuration(), Duration.ofMinutes(1));
    }

    @Test
    void testAddingMultipleTransfersWithTrips() {
        var transfers = new RaptorBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .transfer("A", "B", Duration.ofMinutes(1))
            .transfer("A", "C", Duration.ofMinutes(2))
            .transfer("C", "B", Duration.ofMinutes(3))
            .build()
            .getTransfers();

        Assertions.assertEquals(2, transfers.size());

        var transfersAtA = transfers.get(new StopImpl("A"));

        Assertions.assertEquals(transfersAtA.size(), 2);

        Assertions.assertEquals(transfersAtA.get(0).getOrigin().getName(), "A");
        Assertions.assertEquals(transfersAtA.get(0).getDestination().getName(), "B");
        Assertions.assertEquals(transfersAtA.get(0).getDuration(), Duration.ofMinutes(1));

        Assertions.assertEquals(transfersAtA.get(1).getOrigin().getName(), "A");
        Assertions.assertEquals(transfersAtA.get(1).getDestination().getName(), "C");
        Assertions.assertEquals(transfersAtA.get(1).getDuration(), Duration.ofMinutes(2));

        var transfersAtC = transfers.get(new StopImpl("C"));

        Assertions.assertEquals(transfersAtC.size(), 1);

        Assertions.assertEquals(transfersAtC.get(0).getOrigin().getName(), "C");
        Assertions.assertEquals(transfersAtC.get(0).getDestination().getName(), "B");
        Assertions.assertEquals(transfersAtC.get(0).getDuration(), Duration.ofMinutes(3));
    }

    @Test
    void testStopsRequired() {
        var thrown = Assertions.assertThrows(RaptorBuilderException.class, () -> {
            new RaptorBuilder()
                .trip(t -> {
                })
                .build();
        });

        Assertions.assertEquals(thrown.getMessage(), "Trip has no stops");
    }

    @Test
    void testFirstStopNeedsToArriveAtNull() {
        var thrown = Assertions.assertThrows(RaptorBuilderException.class, () -> {
            new RaptorBuilder()
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
        var thrown = Assertions.assertThrows(RaptorBuilderException.class, () -> {
            new RaptorBuilder()
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
        var thrown = Assertions.assertThrows(RaptorBuilderException.class, () -> {
            new RaptorBuilder()
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
        var thrown = Assertions.assertThrows(RaptorBuilderException.class, () -> {
            new RaptorBuilder()
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
        var thrown = Assertions.assertThrows(RaptorBuilderException.class, () -> {
            new RaptorBuilder()
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
