package com.raoulvdberge.raptor.model;

import java.time.Duration;

public class TransferLeg<S> extends Leg<S> {
    private final Duration duration;

    public TransferLeg(S origin, S destination, Duration duration) {
        super(origin, destination);

        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }
}
