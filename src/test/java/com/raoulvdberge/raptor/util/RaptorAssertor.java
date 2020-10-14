package com.raoulvdberge.raptor.util;

import com.raoulvdberge.raptor.model.Journey;
import com.raoulvdberge.raptor.model.impl.StopImpl;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaptorAssertor {
    private final List<Journey<StopImpl>> journeys;
    private int currentJourney;

    private RaptorAssertor(List<Journey<StopImpl>> journeys) {
        this.journeys = journeys;
    }

    public static RaptorAssertor forJourneys(List<Journey<StopImpl>> journeys) {
        return new RaptorAssertor(journeys);
    }

    public RaptorAssertor assertJourneyCount(int journeyCount) {
        assertEquals(journeyCount, journeys.size());

        return this;
    }

    public RaptorAssertor journey(Consumer<JourneyAssertor> assertorConsumer) {
        var journey = journeys.get(this.currentJourney);

        var assertor = new JourneyAssertor(journey);

        assertorConsumer.accept(assertor);

        this.currentJourney++;

        return this;
    }
}
