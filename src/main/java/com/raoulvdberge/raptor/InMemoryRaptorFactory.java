package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.StopTime;
import com.raoulvdberge.raptor.model.TransferLeg;
import com.raoulvdberge.raptor.model.Trip;
import com.raoulvdberge.raptor.provider.impl.InMemoryRouteDetailsProvider;
import com.raoulvdberge.raptor.provider.impl.InMemoryStopDetailsProvider;
import com.raoulvdberge.raptor.provider.impl.InMemoryTransferDetailsProvider;
import com.raoulvdberge.raptor.provider.impl.InMemoryTripDetailsProvider;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A factory that creates a {@link Raptor} instance for given trips and transfers.
 * This uses the various in-memory provider implementations.
 * This factory should only be used on small datasets for testing purposes, as it's not performant.
 *
 * @param <T> the trip
 * @param <S> the stop
 */
public class InMemoryRaptorFactory<T extends Trip<S>, S> {
    private final List<T> trips;
    private final Map<S, List<TransferLeg<S>>> transfers;

    public InMemoryRaptorFactory(List<T> trips, Map<S, List<TransferLeg<S>>> transfers) {
        this.trips = trips;
        this.transfers = transfers;
    }

    public Raptor<String, S> create() {
        var routeStopIndex = new HashMap<String, Map<S, Integer>>();
        var routePaths = new HashMap<String, List<S>>();
        var routesByStop = new HashMap<S, Set<String>>();
        var tripsByRoute = new HashMap<String, List<Trip<S>>>();
        var tripStopTimes = new HashMap<Trip<S>, List<StopTime<S>>>();

        for (var trip : this.trips) {
            var path = trip.getStopTimes().stream().map(StopTime::getStop).collect(Collectors.toList());
            var route = path.stream().map(S::toString).collect(Collectors.joining("->"));

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
            new InMemoryRouteDetailsProvider<>(routeStopIndex, routePaths),
            new InMemoryTripDetailsProvider<>(tripsByRoute, tripStopTimes),
            new InMemoryStopDetailsProvider<>(routesByStop, stops),
            new InMemoryTransferDetailsProvider<>(this.transfers)
        );
    }
}
