package com.raoulvdberge.raptor.model;

import java.util.List;

public class Journey<S> {
    private final List<Leg<S>> legs;

    public Journey(List<Leg<S>> legs) {
        this.legs = legs;
    }

    public List<Leg<S>> getLegs() {
        return legs;
    }
}
