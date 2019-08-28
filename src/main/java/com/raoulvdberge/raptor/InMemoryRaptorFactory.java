package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRaptorFactory implements RaptorFactory {
    private final List<Trip> trips;
    private final Map<Stop, List<TransferLeg>> transfers;

    public InMemoryRaptorFactory(List<Trip> trips, Map<Stop, List<TransferLeg>> transfers) {
        this.trips = trips;
        this.transfers = transfers;
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
            new InMemoryTripDetailsProvider(tripsByRoute, tripStopTimes),
            new InMemoryStopDetailsProvider(routesByStop, stops),
            new InMemoryTransferDetailsProvider(this.transfers)
        );
    }
}
