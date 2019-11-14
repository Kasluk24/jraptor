package com.raoulvdberge.raptor.model;

/**
 * Describes a leg from origin to destination.
 *
 * @param <S> the stop
 */
public abstract class Leg<S> {
    private final S origin;
    private final S destination;

    public Leg(S origin, S destination) {
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * @return the origin
     */
    public S getOrigin() {
        return origin;
    }

    /**
     * @return the destination
     */
    public S getDestination() {
        return destination;
    }
}
