package com.raoulvdberge.raptor.model;

import java.time.Duration;
import java.util.List;

public class KConnection<S> {
    private final KConnectionType type;

    private final List<StopTime<S>> stopTimes;
    private final int boardingPoint;
    private final int stopIndex;

    private final S origin;
    private final S destination;
    private final Duration duration;

    public KConnection(List<StopTime<S>> stopTimes, int boardingPoint, int stopIndex) {
        this.type = KConnectionType.TIMETABLE;

        this.stopTimes = stopTimes;
        this.boardingPoint = boardingPoint;
        this.stopIndex = stopIndex;

        this.origin = null;
        this.destination = null;
        this.duration = null;
    }

    public KConnection(S origin, S destination, Duration duration) {
        this.type = KConnectionType.TRANSFER;

        this.stopTimes = null;
        this.boardingPoint = 0;
        this.stopIndex = 0;

        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
    }

    public List<StopTime<S>> getStopTimes() {
        return stopTimes;
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
}
