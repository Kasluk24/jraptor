package com.raoulvdberge.raptor.model;

import java.time.LocalDateTime;

public class StopTime {
    private final Stop stop;
    private final LocalDateTime arrivalTime;
    private final LocalDateTime departureTime;

    public StopTime(Stop stop, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.stop = stop;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Stop getStop() {
        return stop;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
}
