package com.raoulvdberge.raptor.model;

import java.util.List;

/**
 * Describes a trip.
 *
 * @param <S> the stop
 */
public interface Trip<S> {
    /**
     * @return the stop times of this trip
     */
    List<StopTime<S>> getStopTimes();
}
