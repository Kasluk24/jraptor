package com.raoulvdberge.raptor.builder;

import com.raoulvdberge.raptor.model.impl.StopImpl;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.raoulvdberge.raptor.util.TimeUtil.nullTime;
import static com.raoulvdberge.raptor.util.TimeUtil.time;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertEquals(1, trips.size());

        var stopTimes = trips.get(0).getStopTimes();

        assertEquals(3, stopTimes.size());
        assertEquals("A", stopTimes.get(0).getStop().getName());
        assertEquals("B", stopTimes.get(1).getStop().getName());
        assertEquals("C", stopTimes.get(2).getStop().getName());
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

        assertEquals(2, trips.size());

        var stopTimes = trips.get(0).getStopTimes();

        assertEquals(3, stopTimes.size());
        assertEquals("A", stopTimes.get(0).getStop().getName());
        assertEquals("B", stopTimes.get(1).getStop().getName());
        assertEquals("C", stopTimes.get(2).getStop().getName());

        stopTimes = trips.get(1).getStopTimes();

        assertEquals(3, stopTimes.size());
        assertEquals("D", stopTimes.get(0).getStop().getName());
        assertEquals("E", stopTimes.get(1).getStop().getName());
        assertEquals("F", stopTimes.get(2).getStop().getName());
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

        assertEquals(1, transfers.size());

        var transfersAtA = transfers.get(new StopImpl("A"));

        assertEquals(1, transfersAtA.size());
        assertEquals("A", transfersAtA.get(0).getOrigin().getName());
        assertEquals("C", transfersAtA.get(0).getDestination().getName());
        assertEquals(Duration.ofMinutes(1), transfersAtA.get(0).getDuration());
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

        assertEquals(2, transfers.size());

        var transfersAtA = transfers.get(new StopImpl("A"));

        assertEquals(2, transfersAtA.size());

        assertEquals("A", transfersAtA.get(0).getOrigin().getName());
        assertEquals("B", transfersAtA.get(0).getDestination().getName());
        assertEquals(Duration.ofMinutes(1), transfersAtA.get(0).getDuration());

        assertEquals("A", transfersAtA.get(1).getOrigin().getName());
        assertEquals("C", transfersAtA.get(1).getDestination().getName());
        assertEquals(Duration.ofMinutes(2), transfersAtA.get(1).getDuration());

        var transfersAtC = transfers.get(new StopImpl("C"));

        assertEquals(1, transfersAtC.size());

        assertEquals("C", transfersAtC.get(0).getOrigin().getName());
        assertEquals("B", transfersAtC.get(0).getDestination().getName());
        assertEquals(Duration.ofMinutes(3), transfersAtC.get(0).getDuration());
    }

    @Test
    void testStopsRequired() {
        var builder = new RaptorBuilder();
        var e = assertThrows(RaptorBuilderException.class, () -> builder.trip(t -> {
        }));

        assertEquals("Trip has no stops", e.getMessage());
    }

    @Test
    void testFirstStopNeedsToArriveAtNull() {
        var builder = new RaptorBuilder();
        var e = assertThrows(RaptorBuilderException.class, () -> builder.trip(t -> t.stop("A", time(10, 0), time(10, 0))));

        assertEquals("The first stop needs to arrive at 0", e.getMessage());
    }

    @Test
    void testIntermediateStopNeedsToNotArriveAtNull() {
        var builder = new RaptorBuilder();
        var e = assertThrows(RaptorBuilderException.class, () -> builder
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", nullTime(), time(10, 6))));

        assertEquals("Already added the first stop, we can't arrive at 0 again", e.getMessage());
    }

    @Test
    void testFirstStopNeedsToNotDepartAtNull() {
        var builder = new RaptorBuilder();
        var e = assertThrows(RaptorBuilderException.class, () -> builder.trip(t -> t.stop("A", nullTime(), nullTime())));

        assertEquals("The first stop can't depart at 0", e.getMessage());
    }

    @Test
    void testLastStopNeedsToDepartAtNull() {
        var builder = new RaptorBuilder();
        var e = assertThrows(RaptorBuilderException.class, ()->builder.trip(t -> t
            .stop("A", nullTime(), time(10, 0))
            .stop("B", time(10, 5), time(10, 6))
            .stop("C", time(10, 10), time(10, 11))));

        assertEquals("The last stop needs to depart at 0", e.getMessage());
    }

    @Test
    void testCannotAddStopsAfterStopWithDepartureAsNull() {
        var builder = new RaptorBuilder();
        var e = assertThrows(RaptorBuilderException.class, () -> builder.trip(t -> t
            .stop("A", nullTime(), time(10, 0))
            .stop("B", time(10, 5), time(10, 6))
            .stop("C", time(10, 10), nullTime())
            .stop("D", time(10, 10), nullTime())));

        assertEquals("Already added the last stop, can't add another one", e.getMessage());
    }
}
