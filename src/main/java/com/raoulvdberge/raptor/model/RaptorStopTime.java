package com.raoulvdberge.raptor.model;

import java.time.LocalDateTime;

public class RaptorStopTime {
    private final RaptorStop stop;
    private final LocalDateTime arrivalTime;
    private final LocalDateTime departureTime;

    public RaptorStopTime(RaptorStop stop, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.stop = stop;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public RaptorStop getStop() {
        return stop;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
}
