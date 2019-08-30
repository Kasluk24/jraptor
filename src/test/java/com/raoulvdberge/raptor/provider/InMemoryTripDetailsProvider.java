package com.raoulvdberge.raptor.provider;

import com.raoulvdberge.raptor.model.Stop;
import com.raoulvdberge.raptor.model.StopTime;
import com.raoulvdberge.raptor.model.Trip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTripDetailsProvider implements TripDetailsProvider<String, Stop> {
    private final Map<String, List<Trip<Stop>>> tripsByRoute;
    private final Map<Trip<Stop>, List<StopTime<Stop>>> tripStopTimes;

    public InMemoryTripDetailsProvider(Map<String, List<Trip<Stop>>> tripsByRoute, Map<Trip<Stop>, List<StopTime<Stop>>> tripStopTimes) {
        this.tripsByRoute = tripsByRoute;
        this.tripStopTimes = tripStopTimes;
    }

    @Override
    public Optional<Trip<Stop>> getEarliestTripAtStop(String route, int stopIndex, LocalDateTime time) {
        return this.tripsByRoute
            .get(route)
            .stream()
            .filter(t -> this.tripStopTimes.get(t).get(stopIndex).getDepartureTime().isAfter(time))
            .findFirst();
    }
}
