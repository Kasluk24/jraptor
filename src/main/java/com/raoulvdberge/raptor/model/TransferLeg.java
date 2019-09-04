package com.raoulvdberge.raptor.model;

import java.time.Duration;

public class TransferLeg<S> extends Leg<S> {
    private final Duration duration;
    private final double distance;

    public TransferLeg(S origin, S destination, Duration duration, double distance) {
        super(origin, destination);

        this.duration = duration;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }
}
