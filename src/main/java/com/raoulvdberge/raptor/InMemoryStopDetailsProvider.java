package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorRoute;
import com.raoulvdberge.raptor.model.RaptorStop;

import java.util.Map;
import java.util.Set;

public class InMemoryStopDetailsProvider implements StopDetailsProvider {
    private final Map<RaptorStop, Set<RaptorRoute>> routesByStop;
    private final Set<RaptorStop> stops;

    public InMemoryStopDetailsProvider(Map<RaptorStop, Set<RaptorRoute>> routesByStop, Set<RaptorStop> stops) {
        this.routesByStop = routesByStop;
        this.stops = stops;
    }

    @Override
    public Set<RaptorStop> getStops() {
        return stops;
    }

    @Override
    public Set<RaptorRoute> getRoutesByStop(RaptorStop stop) {
        return this.routesByStop.get(stop);
    }
}
