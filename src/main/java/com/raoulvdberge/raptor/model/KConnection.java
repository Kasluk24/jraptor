package com.raoulvdberge.raptor.model;

import java.util.List;

public class KConnection {
    private final List<StopTime> stopTimes;
    private final int boardingPoint;
    private final int stopIndex;

    public KConnection(List<StopTime> stopTimes, int boardingPoint, int stopIndex) {
        this.stopTimes = stopTimes;
        this.boardingPoint = boardingPoint;
        this.stopIndex = stopIndex;
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
}
