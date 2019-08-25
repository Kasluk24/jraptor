package com.raoulvdberge.raptor.model;

import java.util.List;

public class Trip {
    private final int id;
    private final List<StopTime> stopTimes;

    public Trip(int id, List<StopTime> stopTimes) {
        this.id = id;
        this.stopTimes = stopTimes;
    }

    public int getId() {
        return id;
    }

    public List<StopTime> getStopTimes() {
        return stopTimes;
    }
}
