package com.raoulvdberge.raptor.model;

import java.time.Duration;

public class RaptorTransferLeg extends RaptorLeg {
    private final Duration duration;

    public RaptorTransferLeg(RaptorStop origin, RaptorStop destination, Duration duration) {
        super(origin, destination);

        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }
}
