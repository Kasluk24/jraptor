package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Route;
import com.raoulvdberge.raptor.model.StopTime;
import com.raoulvdberge.raptor.model.Trip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTripDetailsProvider implements TripDetailsProvider {
    private final Map<Route, List<Trip>> tripsByRoute;
    private final Map<Trip, List<StopTime>> tripStopTimes;

    public InMemoryTripDetailsProvider(Map<Route, List<Trip>> tripsByRoute, Map<Trip, List<StopTime>> tripStopTimes) {
        this.tripsByRoute = tripsByRoute;
        this.tripStopTimes = tripStopTimes;
    }

    @Override
    public Optional<Trip> getEarliestTripAtStop(Route route, int stopIndex, LocalDateTime time) {
        return this.tripsByRoute
            .get(route)
            .stream()
            .filter(t -> this.tripStopTimes.get(t).get(stopIndex).getDepartureTime().isAfter(time))
            .findFirst();
    }
}
