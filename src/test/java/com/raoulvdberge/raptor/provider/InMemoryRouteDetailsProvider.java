package com.raoulvdberge.raptor.provider;

import com.raoulvdberge.raptor.model.Stop;

import java.util.List;
import java.util.Map;

public class InMemoryRouteDetailsProvider implements RouteDetailsProvider<String, Stop> {
    private final Map<String, Map<Stop, Integer>> routeStopIndex;
    private final Map<String, List<Stop>> routePaths;

    public InMemoryRouteDetailsProvider(Map<String, Map<Stop, Integer>> routeStopIndex, Map<String, List<Stop>> routePaths) {
        this.routeStopIndex = routeStopIndex;
        this.routePaths = routePaths;
    }

    @Override
    public boolean isStopBefore(String route, Stop stopA, Stop stopB) {
        return this.routeStopIndex.get(route).get(stopA) < this.routeStopIndex.get(route).get(stopB);
    }

    @Override
    public int getRouteStopIndex(String route, Stop stop) {
        return this.routeStopIndex.get(route).get(stop);
    }

    @Override
    public List<Stop> getRoutePath(String route) {
        return this.routePaths.get(route);
    }
}
