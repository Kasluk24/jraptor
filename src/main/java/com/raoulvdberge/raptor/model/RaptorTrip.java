package com.raoulvdberge.raptor.model;

import java.util.List;

public class RaptorTrip {
    private final int id;
    private final List<RaptorStopTime> stopTimes;

    public RaptorTrip(int id, List<RaptorStopTime> stopTimes) {
        this.id = id;
        this.stopTimes = stopTimes;
    }

    public int getId() {
        return id;
    }

    public List<RaptorStopTime> getStopTimes() {
        return stopTimes;
    }
}
