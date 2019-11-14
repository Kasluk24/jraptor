package com.raoulvdberge.raptor.provider;

import java.util.Set;

/**
 * Provider class for the Raptor algorithm used to provide information about stops.
 *
 * @param <S> the stop
 * @param <R> the route
 */
public interface StopDetailsProvider<S, R> {
    /**
     * @return all the stops
     */
    Set<S> getStops();

    /**
     * Returns all routes that stop at a given stop.
     *
     * @param stop the stop
     * @return the routes that stop at this stop
     */
    Set<R> getRoutesByStop(S stop);
}
