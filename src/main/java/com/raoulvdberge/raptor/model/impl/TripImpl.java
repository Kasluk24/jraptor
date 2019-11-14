package com.raoulvdberge.raptor.model.impl;

import com.raoulvdberge.raptor.model.StopTime;
import com.raoulvdberge.raptor.model.Trip;

import java.util.List;

public class TripImpl implements Trip<StopImpl> {
    private final List<StopTime<StopImpl>> stopTimes;

    public TripImpl(List<StopTime<StopImpl>> stopTimes) {
        this.stopTimes = stopTimes;
    }

    @Override
    public List<StopTime<StopImpl>> getStopTimes() {
        return stopTimes;
    }
}
