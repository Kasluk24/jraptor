package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Journey;
import org.junit.jupiter.api.Assertions;

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
}
