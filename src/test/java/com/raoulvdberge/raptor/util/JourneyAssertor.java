package com.raoulvdberge.raptor.util;

import com.raoulvdberge.raptor.model.Journey;
import com.raoulvdberge.raptor.model.TimetableLeg;
import com.raoulvdberge.raptor.model.TransferLeg;
import com.raoulvdberge.raptor.model.impl.StopImpl;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JourneyAssertor {
    private final Journey<StopImpl> journey;
    private int currentLeg;

    JourneyAssertor(Journey<StopImpl> journey) {
        this.journey = journey;
    }

    public JourneyAssertor assertLegCount(int legCount) {
        assertEquals(legCount, this.journey.getLegs().size());

        return this;
    }

    public JourneyAssertor assertLeg(String origin, String destination) {
        var leg = this.journey.getLegs().get(currentLeg);

        assertEquals(origin, leg.getOrigin().getName());
        assertEquals(destination, leg.getDestination().getName());

        this.currentLeg++;

        return this;
    }

    public JourneyAssertor assertLegTransfer(String origin, String destination, Duration duration) {
        this.assertLeg(origin, destination);

        var leg = (TransferLeg) this.journey.getLegs().get(currentLeg - 1);

        assertEquals(duration, leg.getDuration());

        return this;
    }

    public JourneyAssertor assertLegTimetable(String origin, LocalDateTime originDeparture, String destination, LocalDateTime destinationArrival) {
        this.assertLeg(origin, destination);

        var leg = (TimetableLeg<StopImpl>) this.journey.getLegs().get(currentLeg - 1);

        var stopTimeAtOrigin = leg.getTrip().getStopTimes().stream().filter(st -> st.getStop().getName().equals(origin)).findFirst().orElseThrow();
        var stopTimeAtDestination = leg.getTrip().getStopTimes().stream().filter(st -> st.getStop().getName().equals(destination)).findFirst().orElseThrow();

        assertEquals(originDeparture, stopTimeAtOrigin.getDepartureTime());
        assertEquals(destinationArrival, stopTimeAtDestination.getArrivalTime());

        return this;
    }
}

