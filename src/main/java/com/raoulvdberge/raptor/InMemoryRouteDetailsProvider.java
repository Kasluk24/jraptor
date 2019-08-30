package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorRoute;
import com.raoulvdberge.raptor.model.RaptorStop;

import java.util.List;
import java.util.Map;

public class InMemoryRouteDetailsProvider implements RouteDetailsProvider {
    private final Map<RaptorRoute, Map<RaptorStop, Integer>> routeStopIndex;
    private final Map<RaptorRoute, List<RaptorStop>> routePaths;

    public InMemoryRouteDetailsProvider(Map<RaptorRoute, Map<RaptorStop, Integer>> routeStopIndex, Map<RaptorRoute, List<RaptorStop>> routePaths) {
        this.routeStopIndex = routeStopIndex;
        this.routePaths = routePaths;
    }

    @Override
    public boolean isStopBefore(RaptorRoute route, RaptorStop stopA, RaptorStop stopB) {
        return this.routeStopIndex.get(route).get(stopA) < this.routeStopIndex.get(route).get(stopB);
    }

    @Override
    public int getRouteStopIndex(RaptorRoute route, RaptorStop stop) {
        return this.routeStopIndex.get(route).get(stop);
    }

    @Override
    public List<RaptorStop> getRoutePath(RaptorRoute route) {
        return this.routePaths.get(route);
    }
}
