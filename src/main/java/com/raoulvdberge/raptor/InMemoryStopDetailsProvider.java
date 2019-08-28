package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Route;
import com.raoulvdberge.raptor.model.Stop;

import java.util.Map;
import java.util.Set;

public class InMemoryStopDetailsProvider implements StopDetailsProvider {
    private final Map<Stop, Set<Route>> routesByStop;
    private final Set<Stop> stops;

    public InMemoryStopDetailsProvider(Map<Stop, Set<Route>> routesByStop, Set<Stop> stops) {
        this.routesByStop = routesByStop;
        this.stops = stops;
    }

    @Override
    public Set<Stop> getStops() {
        return stops;
    }

    @Override
    public Set<Route> getRoutesByStop(Stop stop) {
        return this.routesByStop.get(stop);
    }
}
