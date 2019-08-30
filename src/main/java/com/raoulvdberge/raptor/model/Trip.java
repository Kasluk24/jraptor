package com.raoulvdberge.raptor.model;

import java.util.List;

public interface Trip<S> {
    List<StopTime<S>> getStopTimes();
}
