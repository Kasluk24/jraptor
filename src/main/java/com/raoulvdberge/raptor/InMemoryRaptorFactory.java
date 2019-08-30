package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRaptorFactory implements RaptorFactory {
    private final List<RaptorTrip> trips;
    private final Map<RaptorStop, List<RaptorTransferLeg>> transfers;

    public InMemoryRaptorFactory(List<RaptorTrip> trips, Map<RaptorStop, List<RaptorTransferLeg>> transfers) {
        this.trips = trips;
        this.transfers = transfers;
    }

    @Override
    public Raptor create() {
        var routeStopIndex = new HashMap<RaptorRoute, Map<RaptorStop, Integer>>();
        var routePaths = new HashMap<RaptorRoute, List<RaptorStop>>();
        var routesByStop = new HashMap<RaptorStop, Set<RaptorRoute>>();
        var tripsByRoute = new HashMap<RaptorRoute, List<RaptorTrip>>();
        var tripStopTimes = new HashMap<RaptorTrip, List<RaptorStopTime>>();

        for (var trip : this.trips) {
            var path = trip.getStopTimes().stream().map(RaptorStopTime::getStop).collect(Collectors.toList());
            var route = new RaptorRoute(0, path.stream().map(RaptorStop::getName).collect(Collectors.joining("->")));

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
