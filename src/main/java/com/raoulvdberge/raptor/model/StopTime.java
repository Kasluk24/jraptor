package com.raoulvdberge.raptor.model;

import java.time.LocalDateTime;

/**
 * Describes a stop where a trip stops. Has an arrival and departure time.
 *
 * @param <S> the stop
 */
public class StopTime<S> {
    private final S stop;
    private final LocalDateTime arrivalTime;
    private final LocalDateTime departureTime;

    public StopTime(S stop, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.stop = stop;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    /**
     * @return the stop
     */
    public S getStop() {
        return stop;
    }

    /**
     * @return the arrival time
     */
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @return the departure time
     */
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
}
