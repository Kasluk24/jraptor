package com.raoulvdberge.raptor.provider.impl;

import com.raoulvdberge.raptor.provider.StopDetailsProvider;

import java.util.Map;
import java.util.Set;

public class InMemoryStopDetailsProvider<S> implements StopDetailsProvider<S, String> {
    private final Map<S, Set<String>> routesByStop;
    private final Set<S> stops;

    /**
     * Routes by stop is a map where the key is the stop, and the value a set of routes that stop at this stop.
     *
     * @param routesByStop the routes by stop
     * @param stops        the stops
     */
    public InMemoryStopDetailsProvider(Map<S, Set<String>> routesByStop, Set<S> stops) {
        this.routesByStop = routesByStop;
        this.stops = stops;
    }

    @Override
    public Set<S> getStops() {
        return stops;
    }

    @Override
    public Set<String> getRoutesByStop(S stop) {
        return this.routesByStop.get(stop);
    }
}
