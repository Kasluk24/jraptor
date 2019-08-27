package com.raoulvdberge.raptor.model;

import java.util.List;

public class TimetableLeg extends Leg {
    private final List<StopTime> stopTimes;

    public TimetableLeg(Stop origin, Stop destination, List<StopTime> stopTimes) {
        super(origin, destination);
        this.stopTimes = stopTimes;
    }

    public List<StopTime> getStopTimes() {
        return stopTimes;
    }
}
