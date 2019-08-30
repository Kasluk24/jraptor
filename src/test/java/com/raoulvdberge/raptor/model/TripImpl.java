package com.raoulvdberge.raptor.model;

import java.util.List;

public class TripImpl implements Trip<Stop> {
    private final List<StopTime<Stop>> stopTimes;

    public TripImpl(List<StopTime<Stop>> stopTimes) {
        this.stopTimes = stopTimes;
    }

    @Override
    public List<StopTime<Stop>> getStopTimes() {
        return stopTimes;
    }
}
