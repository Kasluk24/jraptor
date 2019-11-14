package com.raoulvdberge.raptor.model;

import java.time.Duration;

/**
 * Describes a leg that isn't performed by a trip, but rather by walking or other means of transportation.
 *
 * @param <S> the stop
 */
public class TransferLeg<S> extends Leg<S> {
    private final Duration duration;
    private final double distance;

    public TransferLeg(S origin, S destination, Duration duration, double distance) {
        super(origin, destination);

        this.duration = duration;
        this.distance = distance;
    }

    /**
     * The distance of this leg. The distance unit isn't defined, and is defined by the creator of this leg.
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @return the duration
     */
    public Duration getDuration() {
        return duration;
    }
}
