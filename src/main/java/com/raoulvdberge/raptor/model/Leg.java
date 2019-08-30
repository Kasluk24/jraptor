package com.raoulvdberge.raptor.model;

public abstract class Leg<S> {
    private final S origin;
    private final S destination;

    public Leg(S origin, S destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public S getOrigin() {
        return origin;
    }

    public S getDestination() {
        return destination;
    }
}
