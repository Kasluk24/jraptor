package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.*;
import com.raoulvdberge.raptor.provider.InMemoryRouteDetailsProvider;
import com.raoulvdberge.raptor.provider.InMemoryStopDetailsProvider;
import com.raoulvdberge.raptor.provider.InMemoryTransferDetailsProvider;
import com.raoulvdberge.raptor.provider.InMemoryTripDetailsProvider;

import java.util.*;
import java.util.stream.Collectors;

class InMemoryRaptorFactory {
    private final List<TripImpl> trips;
    private final Map<Stop, List<TransferLeg<Stop>>> transfers;

    InMemoryRaptorFactory(List<TripImpl> trips, Map<Stop, List<TransferLeg<Stop>>> transfers) {
        this.trips = trips;
        this.transfers = transfers;
    }

    Raptor<String, Stop> create() {
        var routeStopIndex = new HashMap<String, Map<Stop, Integer>>();
        var routePaths = new HashMap<String, List<Stop>>();
        var routesByStop = new HashMap<Stop, Set<String>>();
        var tripsByRoute = new HashMap<String, List<Trip<Stop>>>();
        var tripStopTimes = new HashMap<Trip<Stop>, List<StopTime<Stop>>>();

        for (var trip : this.trips) {
            var path = trip.getStopTimes().stream().map(StopTime::getStop).collect(Collectors.toList());
            var route = path.stream().map(Stop::getName).collect(Collectors.joining("->"));

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

        return new Raptor<>(
            new InMemoryRouteDetailsProvider(routeStopIndex, routePaths),
            new InMemoryTripDetailsProvider(tripsByRoute, tripStopTimes),
            new InMemoryStopDetailsProvider(routesByStop, stops),
            new InMemoryTransferDetailsProvider(this.transfers)
        );
    }
}
