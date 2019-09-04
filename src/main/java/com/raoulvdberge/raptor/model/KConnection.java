package com.raoulvdberge.raptor.model;

import java.time.Duration;

public class KConnection<S> {
    private final KConnectionType type;

    private final Trip<S> trip;
    private final int boardingPoint;
    private final int stopIndex;

    private final S origin;
    private final S destination;
    private final Duration duration;
    private final double distance;

    public KConnection(Trip<S> trip, int boardingPoint, int stopIndex) {
        this.type = KConnectionType.TIMETABLE;

        this.trip = trip;
        this.boardingPoint = boardingPoint;
        this.stopIndex = stopIndex;

        this.origin = null;
        this.destination = null;
        this.duration = null;
        this.distance = 0;
    }

    public KConnection(S origin, S destination, Duration duration, double distance) {
        this.type = KConnectionType.TRANSFER;

        this.trip = null;
        this.boardingPoint = 0;
        this.stopIndex = 0;

        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.distance = distance;
    }

    public Trip<S> getTrip() {
        return trip;
    }

    public int getBoardingPoint() {
        return boardingPoint;
    }

    public int getStopIndex() {
        return stopIndex;
    }

    public KConnectionType getType() {
        return type;
    }

    public S getOrigin() {
        return origin;
    }

    public S getDestination() {
        return destination;
    }

    public Duration getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }
}
