package com.raoulvdberge.raptor.provider;

import com.raoulvdberge.raptor.model.Stop;

import java.util.Map;
import java.util.Set;

public class InMemoryStopDetailsProvider implements StopDetailsProvider<Stop, String> {
    private final Map<Stop, Set<String>> routesByStop;
    private final Set<Stop> stops;

    public InMemoryStopDetailsProvider(Map<Stop, Set<String>> routesByStop, Set<Stop> stops) {
        this.routesByStop = routesByStop;
        this.stops = stops;
    }

    @Override
    public Set<Stop> getStops() {
        return stops;
    }

    @Override
    public Set<String> getRoutesByStop(Stop stop) {
        return this.routesByStop.get(stop);
    }
}
