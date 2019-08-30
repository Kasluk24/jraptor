package com.raoulvdberge.raptor.model;

import java.time.LocalDateTime;

public class StopTime<S> {
    private final S stop;
    private final LocalDateTime arrivalTime;
    private final LocalDateTime departureTime;

    public StopTime(S stop, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.stop = stop;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public S getStop() {
        return stop;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
}
