package com.raoulvdberge.raptor.model;

public abstract class Leg {
    private final Stop origin;
    private final Stop destination;

    public Leg(Stop origin, Stop destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Stop getOrigin() {
        return origin;
    }

    public Stop getDestination() {
        return destination;
    }
}
