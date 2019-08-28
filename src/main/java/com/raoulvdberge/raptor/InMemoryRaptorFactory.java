package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Route;
import com.raoulvdberge.raptor.model.Stop;
import com.raoulvdberge.raptor.model.StopTime;
import com.raoulvdberge.raptor.model.Trip;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRaptorFactory implements RaptorFactory {
    private final List<Trip> trips;

    public InMemoryRaptorFactory(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public Raptor create() {
        var routeStopIndex = new HashMap<Route, Map<Stop, Integer>>();
        var routePaths = new HashMap<Route, List<Stop>>();
        var routesByStop = new HashMap<Stop, Set<Route>>();
        var tripsByRoute = new HashMap<Route, List<Trip>>();
        var tripStopTimes = new HashMap<Trip, List<StopTime>>();

        for (var trip : this.trips) {
            var path = trip.getStopTimes().stream().map(StopTime::getStop).collect(Collectors.toList());
            var route = new Route(0, path.stream().map(Stop::getName).collect(Collectors.joining("->")));

            if (!routeStopIndex.containsKey(route)) {
                tripsByRoute.put(route, new ArrayList<>());
                routeStopIndex.put(route, new HashMap<>());
                routePaths.put(route, path);

                for (var i = 0; i < path.size(); ++i) {
                    routeStopIndex.get(route).put(path.get(i), i);
                    routesByStop.putIfAbsent(path.get(i), new HashSet<>());
                    routesByStop.get(path.get(i)).add(route);
                }
            }

            tripStopTimes.put(trip, trip.getStopTimes());
            tripsByRoute.get(route).add(trip);
        }

        var stops = routesByStop.keySet();

        return new Raptor(
            new InMemoryRouteDetailsProvider(routeStopIndex, routePaths),
            routesByStop,
            tripsByRoute,
            tripStopTimes,
            stops
        );
    }
}
