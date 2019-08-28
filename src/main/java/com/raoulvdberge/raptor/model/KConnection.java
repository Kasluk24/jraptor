package com.raoulvdberge.raptor.model;

import java.time.Duration;
import java.util.List;

public class KConnection {
    private final KConnectionType type;

    private final List<StopTime> stopTimes;
    private final int boardingPoint;
    private final int stopIndex;

    private final Stop origin;
    private final Stop destination;
    private final Duration duration;

    public KConnection(List<StopTime> stopTimes, int boardingPoint, int stopIndex) {
        this.type = KConnectionType.TIMETABLE;

        this.stopTimes = stopTimes;
        this.boardingPoint = boardingPoint;
        this.stopIndex = stopIndex;

        this.origin = null;
        this.destination = null;
        this.duration = null;
    }

    public KConnection(Stop origin, Stop destination, Duration duration) {
        this.type = KConnectionType.TRANSFER;

        this.stopTimes = null;
        this.boardingPoint = 0;
        this.stopIndex = 0;

        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
    }

    public List<StopTime> getStopTimes() {
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

    public Stop getOrigin() {
        return origin;
    }

    public Stop getDestination() {
        return destination;
    }

    public Duration getDuration() {
        return duration;
    }
}
