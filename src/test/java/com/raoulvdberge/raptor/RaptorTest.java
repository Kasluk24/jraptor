package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.builder.TripAndTransferBuilder;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.raoulvdberge.raptor.builder.TripAndTransferBuilderTestUtils.nullTime;
import static com.raoulvdberge.raptor.builder.TripAndTransferBuilderTestUtils.time;

class RaptorTest {
    @Test
    void testFindingJourneyWithDirectConnection() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "C", time(9, 59));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(1)
                .assertLegTimetable("A", time(10, 0), "C", time(10, 10)));
    }

    @Test
    void testFindingJourneyWithTransfer() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 1))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 20))
                .stop("E", time(10, 21), time(10, 22))
                .stop("F", time(10, 23), nullTime()))
            .transfer("C", "D", Duration.ofMinutes(1))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "F", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(3)
                .assertLegTimetable("A", time(10, 1), "C", time(10, 10))
                .assertLegTransfer("C", "D", Duration.ofMinutes(1))
                .assertLegTimetable("D", time(10, 20), "F", time(10, 23))
            );
    }

    @Test
    void testNotFindingJourneyWithLateTransfer() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 1))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 20))
                .stop("E", time(10, 21), time(10, 22))
                .stop("F", time(10, 23), nullTime()))
            .transfer("C", "D", Duration.ofMinutes(11))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "F", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(0);
    }

    @Test
    void testFindingLaterJourneyWithLateTransfer() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 1))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 20))
                .stop("E", time(10, 21), time(10, 22))
                .stop("F", time(10, 23), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 23))
                .stop("E", time(10, 24), time(10, 25))
                .stop("F", time(10, 26), nullTime()))
            .transfer("C", "D", Duration.ofMinutes(11))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "F", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(3)
                .assertLegTimetable("A", time(10, 1), "C", time(10, 10))
                .assertLegTransfer("C", "D", Duration.ofMinutes(11))
                .assertLegTimetable("D", time(10, 23), "F", time(10, 26))
            );
    }

    @Test
    void testFindingBestJourneyWithTransfer() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 1))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 20))
                .stop("E", time(10, 21), time(10, 22))
                .stop("F", time(10, 23), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 23))
                .stop("E", time(10, 24), time(10, 25))
                .stop("F", time(10, 26), nullTime()))
            .transfer("C", "D", Duration.ofMinutes(1))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "F", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(3)
                .assertLegTimetable("A", time(10, 1), "C", time(10, 10))
                .assertLegTransfer("C", "D", Duration.ofMinutes(1))
                .assertLegTimetable("D", time(10, 20), "F", time(10, 23))
            );
    }

    @Test
    void testFindingMultipleJourneysWhenTransferOrDirectConnectionCanBeTaken() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 1))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 20), nullTime()))
            .transfer("B", "C", Duration.ofMinutes(1))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "C", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(2)
            .journey(j -> j
                .assertLegCount(1)
                .assertLegTimetable("A", time(10, 1), "C", time(10, 20)))
            .journey(j -> j
                .assertLegCount(2)
                .assertLegTimetable("A", time(10, 1), "B", time(10, 5))
                .assertLegTransfer("B", "C", Duration.ofMinutes(1))
            );
    }

    @Test
    void testFindingSingleJourneyWhenTransferOrMultipleConnectionsCanBeTaken() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 1))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 20), nullTime()))
            .trip(t -> t
                .stop("C", nullTime(), time(10, 22))
                .stop("D", time(10, 30), nullTime()))
            .transfer("C", "D", Duration.ofMinutes(1))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "D", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(2)
                .assertLegTimetable("A", time(10, 1), "C", time(10, 20))
                .assertLegTransfer("C", "D", Duration.ofMinutes(1))
            );
    }

    @Test
    void testFindingJourneyWithSingleConnection() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 0))
                .stop("B", time(10, 3), time(10, 8))
                .stop("E", time(10, 10), nullTime()))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "E", time(9, 59));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(2)
                .assertLegTimetable("A", time(10, 0), "B", time(10, 5))
                .assertLegTimetable("B", time(10, 8), "E", time(10, 10)));
    }

    @Test
    void testNotFindingJourneyThatCannotBeMadeDueToMissingConnection() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("D", nullTime(), time(10, 0))
                .stop("B", time(10, 1), time(10, 2))
                .stop("E", time(10, 10), nullTime()))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "E", time(10, 0));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(0);
    }

    @Test
    void testNotFindingJourneyThatCannotBeMadeDueToLateQuery() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "C", time(10, 1));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(0);
    }

    @Test
    void testFindingFastestAndLeastChanges() {
        var builderResult = new TripAndTransferBuilder()
            .trip(t -> t
                .stop("A", nullTime(), time(10, 0))
                .stop("B", time(10, 5), time(10, 6))
                .stop("C", time(10, 10), nullTime()))
            .trip(t -> t
                .stop("B", nullTime(), time(10, 6))
                .stop("C", time(10, 7), nullTime()))
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "C", time(9, 59));

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
        var builderResult = new TripAndTransferBuilder()
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
            .build();

        var sut = new InMemoryRaptorFactory(builderResult.getTrips(), builderResult.getTransfers()).create();

        var result = sut.plan("A", "E", time(9, 59));

        JourneyAssertor.forJourneys(result)
            .assertJourneyCount(1)
            .journey(j -> j
                .assertLegCount(2)
                .assertLegTimetable("A", time(10, 0), "G", time(10, 5))
                .assertLegTimetable("G", time(10, 6), "E", time(10, 7)));
    }
}
