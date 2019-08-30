package com.raoulvdberge.raptor.model;

import java.util.List;

public class RaptorTimetableLeg extends RaptorLeg {
    private final List<RaptorStopTime> stopTimes;

    public RaptorTimetableLeg(RaptorStop origin, RaptorStop destination, List<RaptorStopTime> stopTimes) {
        super(origin, destination);
        this.stopTimes = stopTimes;
    }

    public List<RaptorStopTime> getStopTimes() {
        return stopTimes;
    }
}
