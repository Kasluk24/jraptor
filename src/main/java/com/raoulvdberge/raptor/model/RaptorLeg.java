package com.raoulvdberge.raptor.model;

public abstract class RaptorLeg {
    private final RaptorStop origin;
    private final RaptorStop destination;

    public RaptorLeg(RaptorStop origin, RaptorStop destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public RaptorStop getOrigin() {
        return origin;
    }

    public RaptorStop getDestination() {
        return destination;
    }
}
