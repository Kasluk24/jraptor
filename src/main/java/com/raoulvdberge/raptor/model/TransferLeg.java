package com.raoulvdberge.raptor.model;

import java.time.Duration;

public class TransferLeg extends Leg {
    private final Duration duration;

    public TransferLeg(Stop origin, Stop destination, Duration duration) {
        super(origin, destination);

        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }
}
