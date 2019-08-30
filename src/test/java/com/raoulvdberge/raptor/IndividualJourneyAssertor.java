package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorJourney;
import com.raoulvdberge.raptor.model.RaptorTimetableLeg;
import com.raoulvdberge.raptor.model.RaptorTransferLeg;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.time.LocalDateTime;

class IndividualJourneyAssertor {
    private final RaptorJourney journey;
    private int currentLeg;

    IndividualJourneyAssertor(RaptorJourney journey) {
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

    IndividualJourneyAssertor assertLegTransfer(String origin, String destination, Duration duration) {
        this.assertLeg(origin, destination);

        var leg = (RaptorTransferLeg) this.journey.getLegs().get(currentLeg - 1);

        Assertions.assertEquals(duration, leg.getDuration());
        
        return this;
    }

    IndividualJourneyAssertor assertLegTimetable(String origin, LocalDateTime originDeparture, String destination, LocalDateTime destinationArrival) {
        this.assertLeg(origin, destination);

        var leg = (RaptorTimetableLeg) this.journey.getLegs().get(currentLeg - 1);

        var stopTimeAtOrigin = leg.getStopTimes().stream().filter(st -> st.getStop().getName().equals(origin)).findFirst().orElseThrow();
        var stopTimeAtDestination = leg.getStopTimes().stream().filter(st -> st.getStop().getName().equals(destination)).findFirst().orElseThrow();

        Assertions.assertEquals(originDeparture, stopTimeAtOrigin.getDepartureTime());
        Assertions.assertEquals(destinationArrival, stopTimeAtDestination.getArrivalTime());

        return this;
    }
}
