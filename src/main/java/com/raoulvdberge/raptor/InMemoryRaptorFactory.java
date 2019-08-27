package com.raoulvdberge.raptor;

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
        var routeStopIndex = new HashMap<String, Map<Stop, Integer>>();
        var routePaths = new HashMap<String, List<Stop>>();
        var routesByStop = new HashMap<Stop, Set<String>>();
        var tripsByRoute = new HashMap<String, List<Trip>>();
        var tripStopTimes = new HashMap<Trip, List<StopTime>>();

        for (var trip : this.trips) {
            var path = trip.getStopTimes().stream().map(StopTime::getStop).collect(Collectors.toList());
            var routeId = path.stream().map(Stop::getName).collect(Collectors.joining("->"));

            if (!routeStopIndex.containsKey(routeId)) {
                tripsByRoute.put(routeId, new ArrayList<>());
                routeStopIndex.put(routeId, new HashMap<>());
                routePaths.put(routeId, path);

                for (var i = 0; i < path.size(); ++i) {
                    routeStopIndex.get(routeId).put(path.get(i), i);
                    routesByStop.putIfAbsent(path.get(i), new HashSet<>());
                    routesByStop.get(path.get(i)).add(routeId);
                }
            }

            tripStopTimes.put(trip, trip.getStopTimes());
            tripsByRoute.get(routeId).add(trip);
        }

        var stops = routesByStop.keySet();

        return new Raptor(routeStopIndex, routePaths, routesByStop, tripsByRoute, tripStopTimes, stops);
    }
}
