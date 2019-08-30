package com.raoulvdberge.raptor.model;

import java.util.List;

public class TimetableLeg<S> extends Leg<S> {
    private final List<StopTime<S>> stopTimes;

    public TimetableLeg(S origin, S destination, List<StopTime<S>> stopTimes) {
        super(origin, destination);

        this.stopTimes = stopTimes;
    }

    public List<StopTime<S>> getStopTimes() {
        return stopTimes;
    }
}
