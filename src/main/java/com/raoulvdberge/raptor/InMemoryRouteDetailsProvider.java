package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Route;
import com.raoulvdberge.raptor.model.Stop;

import java.util.List;
import java.util.Map;

public class InMemoryRouteDetailsProvider implements RouteDetailsProvider {
    private final Map<Route, Map<Stop, Integer>> routeStopIndex;
    private final Map<Route, List<Stop>> routePaths;

    public InMemoryRouteDetailsProvider(Map<Route, Map<Stop, Integer>> routeStopIndex, Map<Route, List<Stop>> routePaths) {
        this.routeStopIndex = routeStopIndex;
        this.routePaths = routePaths;
    }

    @Override
    public boolean isStopBefore(Route route, Stop stopA, Stop stopB) {
        return this.routeStopIndex.get(route).get(stopA) < this.routeStopIndex.get(route).get(stopB);
    }

    @Override
    public int getRouteStopIndex(Route route, Stop stop) {
        return this.routeStopIndex.get(route).get(stop);
    }

    @Override
    public List<Stop> getRoutePath(Route route) {
        return this.routePaths.get(route);
    }
}
