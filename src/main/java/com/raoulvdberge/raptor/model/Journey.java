package com.raoulvdberge.raptor.model;

import java.util.List;

public class Journey {
    private final List<Leg> legs;

    public Journey(List<Leg> legs) {
        this.legs = legs;
    }

    public List<Leg> getLegs() {
        return legs;
    }
}
