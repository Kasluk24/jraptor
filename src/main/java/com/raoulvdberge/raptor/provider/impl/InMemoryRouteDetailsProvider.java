package com.raoulvdberge.raptor.provider.impl;

import com.raoulvdberge.raptor.provider.RouteDetailsProvider;

import java.util.List;
import java.util.Map;

public class InMemoryRouteDetailsProvider<S> implements RouteDetailsProvider<String, S> {
    private final Map<String, Map<S, Integer>> routeStopIndex;
    private final Map<String, List<S>> routePaths;

    /**
     * The route stop index is a map where the key is a route identifier, and the value a map where the key is the stop and the value the index of that stop.
     * The route path is a map where the key is a route identifier, and the value the path of stops where the route stops.
     *
     * @param routeStopIndex the route stop index
     * @param routePaths     the route paths
     */
    public InMemoryRouteDetailsProvider(Map<String, Map<S, Integer>> routeStopIndex, Map<String, List<S>> routePaths) {
        this.routeStopIndex = routeStopIndex;
        this.routePaths = routePaths;
    }

    @Override
    public boolean isStopBefore(String route, S stopA, S stopB) {
        return routeStopIndex.get(route).get(stopA) < this.routeStopIndex.get(route).get(stopB);
    }

    @Override
    public int getRouteStopIndex(String route, S stop) {
        return routeStopIndex.get(route).get(stop);
    }

    @Override
    public List<S> getRoutePath(String route) {
        return routePaths.get(route);
    }
}
