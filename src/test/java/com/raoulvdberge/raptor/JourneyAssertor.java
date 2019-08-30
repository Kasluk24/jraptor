package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorJourney;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.function.Consumer;

class JourneyAssertor {
    private final List<RaptorJourney> journeys;
    private int currentJourney;

    private JourneyAssertor(List<RaptorJourney> journeys) {
        this.journeys = journeys;
    }

    static JourneyAssertor forJourneys(List<RaptorJourney> journeys) {
        return new JourneyAssertor(journeys);
    }

    JourneyAssertor assertJourneyCount(int journeyCount) {
        Assertions.assertEquals(journeyCount, this.journeys.size());

        return this;
    }

    JourneyAssertor journey(Consumer<IndividualJourneyAssertor> assertorConsumer) {
        var journey = this.journeys.get(this.currentJourney);

        var assertor = new IndividualJourneyAssertor(journey);

        assertorConsumer.accept(assertor);

        this.currentJourney++;

        return this;
    }
}
