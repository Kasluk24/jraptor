package com.raoulvdberge.raptor.provider.impl;

import com.raoulvdberge.raptor.model.StopTime;
import com.raoulvdberge.raptor.model.Trip;
import com.raoulvdberge.raptor.provider.TripDetailsProvider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTripDetailsProvider<S> implements TripDetailsProvider<String, S> {
    private final Map<String, List<Trip<S>>> tripsByRoute;
    private final Map<Trip<S>, List<StopTime<S>>> tripStopTimes;

    /**
     * Trips by route is a map where the key is a route identifier, and the value a list of trips for that route.
     * Trip stop times is a map where the key is the trip, and the value a list of stop times at the various stops for that trip.
     *
     * @param tripsByRoute  the trips by route
     * @param tripStopTimes the trip stop times
     */
    public InMemoryTripDetailsProvider(Map<String, List<Trip<S>>> tripsByRoute, Map<Trip<S>, List<StopTime<S>>> tripStopTimes) {
        this.tripsByRoute = tripsByRoute;
        this.tripStopTimes = tripStopTimes;
    }

    @Override
    public Optional<Trip<S>> getEarliestTripAtStop(String route, int stopIndex, LocalDateTime time) {
        return tripsByRoute
            .get(route)
            .stream()
            .filter(t -> tripStopTimes.get(t).get(stopIndex).getDepartureTime().isAfter(time))
            .findFirst();
    }
}
