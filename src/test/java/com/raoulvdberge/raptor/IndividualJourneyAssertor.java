package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Journey;
import com.raoulvdberge.raptor.model.TimetableLeg;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

class IndividualJourneyAssertor {
    private final Journey journey;
    private int currentLeg;

    IndividualJourneyAssertor(Journey journey) {
        this.journey = journey;
    }

    IndividualJourneyAssertor assertLegCount(int legCount) {
        Assertions.assertEquals(legCount, this.journey.getLegs().size());

        return this;
    }

    IndividualJourneyAssertor assertLeg(String origin, String destination) {
        var leg = this.journey.getLegs().get(currentLeg);

        Assertions.assertEquals(origin, leg.getOrigin().getName());
        Assertions.assertEquals(destination, leg.getDestination().getName());

        this.currentLeg++;

        return this;
    }

    IndividualJourneyAssertor assertLegTimetable(String origin, LocalDateTime originDeparture, String destination, LocalDateTime destinationArrival) {
        assertLeg(origin, destination);

        var leg = (TimetableLeg) this.journey.getLegs().get(currentLeg - 1);

        var stopTimeAtOrigin = leg.getStopTimes().stream().filter(st -> st.getStop().getName().equals(origin)).findFirst().orElseThrow();
        var stopTimeAtDestination = leg.getStopTimes().stream().filter(st -> st.getStop().getName().equals(destination)).findFirst().orElseThrow();

        Assertions.assertEquals(originDeparture, stopTimeAtOrigin.getDepartureTime());
        Assertions.assertEquals(destinationArrival, stopTimeAtDestination.getArrivalTime());

        return this;
    }
}
