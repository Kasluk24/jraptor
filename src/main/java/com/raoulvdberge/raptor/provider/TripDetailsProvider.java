package com.raoulvdberge.raptor.provider;

import com.raoulvdberge.raptor.model.Trip;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Provider class for the Raptor algorithm used to provide information about trips.
 *
 * @param <R> the route
 * @param <S> the stop
 */
public interface TripDetailsProvider<R, S> {
    /**
     * Returns the earliest trip of a given route at a given stop for a given time. The given stop has to be provided in the form of a stop index.
     * This means that the stop is decided by the position of that stop in the route.
     * The Raptor algorithm uses {@link RouteDetailsProvider#getRoutePath(Object)} to map a stop index to a stop.
     *
     * @param route     the route
     * @param stopIndex the stop
     * @param time      the time
     * @return the earliest trip, or empty if there's no trip
     */
    Optional<Trip<S>> getEarliestTripAtStop(R route, int stopIndex, LocalDateTime time);
}
