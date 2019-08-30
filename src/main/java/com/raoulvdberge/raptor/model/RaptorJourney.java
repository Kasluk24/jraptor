package com.raoulvdberge.raptor.model;

import java.util.List;

public class RaptorJourney {
    private final List<RaptorLeg> legs;

    public RaptorJourney(List<RaptorLeg> legs) {
        this.legs = legs;
    }

    public List<RaptorLeg> getLegs() {
        return legs;
    }
}
