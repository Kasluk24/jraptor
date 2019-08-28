package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.*;

import java.time.LocalDateTime;
import java.util.*;

public class Raptor {
    private final RouteDetailsProvider routeDetailsProvider;
    private final TripDetailsProvider tripDetailsProvider;
    private final StopDetailsProvider stopDetailsProvider;
    private final TransferDetailsProvider transferDetailsProvider;

    public Raptor(RouteDetailsProvider routeDetailsProvider,
                  TripDetailsProvider tripDetailsProvider,
                  StopDetailsProvider stopDetailsProvider,
                  TransferDetailsProvider transferDetailsProvider) {
        this.routeDetailsProvider = routeDetailsProvider;
        this.tripDetailsProvider = tripDetailsProvider;
        this.stopDetailsProvider = stopDetailsProvider;
        this.transferDetailsProvider = transferDetailsProvider;
    }

    public List<Journey> plan(String originName, String destinationName, LocalDateTime date) {
        return plan(
            this.stopDetailsProvider.getStops().stream().filter(s -> s.getName().equals(originName)).findFirst().orElseThrow(),
            this.stopDetailsProvider.getStops().stream().filter(s -> s.getName().equals(destinationName)).findFirst().orElseThrow(),
            date
        );
    }

    public List<Journey> plan(Stop origin, Stop destination, LocalDateTime date) {
        var kArrivals = new HashMap<Integer, Map<Stop, LocalDateTime>>();
        var kConnections = new HashMap<Stop, Map<Integer, KConnection>>();

        for (var stop : this.stopDetailsProvider.getStops()) {
            kConnections.put(stop, new HashMap<>());
        }

        var initialArrivals = new HashMap<Stop, LocalDateTime>();
        for (var stop : this.stopDetailsProvider.getStops()) {
            initialArrivals.put(stop, LocalDateTime.MAX);
        }
        kArrivals.put(0, initialArrivals);
        kArrivals.get(0).put(origin, date);

        var markedStops = new HashSet<Stop>();
        markedStops.add(origin);

        for (var k = 1; !markedStops.isEmpty(); ++k) {
            var newMarkedStops = new HashSet<Stop>();

            var queue = this.getQueue(markedStops);

            kArrivals.put(k, new HashMap<>(kArrivals.get(k - 1)));

            for (var entry : queue.entrySet()) {
                var boardingPoint = -1;
                var route = entry.getKey();
                var stop = entry.getValue();

                Optional<List<StopTime>> stopTimes = Optional.empty();

                var routePath = this.routeDetailsProvider.getRoutePath(route);

                for (var stopIndex = this.routeDetailsProvider.getRouteStopIndex(route, stop); stopIndex < routePath.size(); ++stopIndex) {
                    var stopInRoute = routePath.get(stopIndex);

                    if (stopTimes.isPresent() && stopTimes.get().get(stopIndex).getArrivalTime().isBefore(kArrivals.get(k).get(stopInRoute))) {
                        kArrivals.get(k).put(stopInRoute, stopTimes.get().get(stopIndex).getArrivalTime());
                        kConnections.get(stopInRoute).put(k, new KConnection(
                            stopTimes.get(),
                            boardingPoint,
                            stopIndex
                        ));

                        newMarkedStops.add(stopInRoute);
                    }

                    if (stopTimes.isEmpty() || kArrivals.get(k - 1).get(stopInRoute).isBefore(stopTimes.get().get(stopIndex).getArrivalTime())) {
                        var trip = this.tripDetailsProvider.getEarliestTripAtStop(
                            route,
                            stopIndex,
                            kArrivals.get(k - 1).get(stopInRoute)
                        );

                        if (trip.isPresent()) {
                            stopTimes = Optional.of(trip.get().getStopTimes());
                        }

                        boardingPoint = stopIndex;
                    }
                }
            }

            for (var stop : markedStops) {
                for (var transfer : this.transferDetailsProvider.getTransfersForStop(stop)) {
                    var dest = transfer.getDestination();
                    var arrivalTime = kArrivals.get(k - 1).get(stop).plus(transfer.getDuration());

                    if (arrivalTime.isBefore(kArrivals.get(k).get(dest))) {
                        kArrivals.get(k).put(dest, arrivalTime);
                        kConnections.get(dest).put(k, new KConnection(
                            stop,
                            dest,
                            transfer.getDuration()
                        ));

                        newMarkedStops.add(dest);
                    }
                }
            }

            markedStops = newMarkedStops;
        }

        return this.getResults(kConnections, destination);
    }

    private Map<Route, Stop> getQueue(Set<Stop> markedStops) {
        var queue = new HashMap<Route, Stop>();

        for (var stop : markedStops) {
            for (var route : this.stopDetailsProvider.getRoutesByStop(stop)) {
                if (!queue.containsKey(route) || this.routeDetailsProvider.isStopBefore(route, stop, queue.get(route))) {
                    queue.put(route, stop);
                }
            }
        }

        return queue;
    }

    private List<Journey> getResults(HashMap<Stop, Map<Integer, KConnection>> kConnections, Stop finalDestination) {
        var results = new ArrayList<Journey>();

        for (var k : kConnections.get(finalDestination).keySet()) {
            var dest = finalDestination;

            var legs = new ArrayList<Leg>();

            while (k > 0) {
                var connection = kConnections.get(dest).get(k);

                if (connection.getType() == KConnectionType.TRANSFER) {
                    legs.add(new TransferLeg(connection.getOrigin(), connection.getDestination(), connection.getDuration()));

                    dest = connection.getOrigin();
                } else {
                    var stopTimes = connection.getStopTimes();

                    var origin = stopTimes.get(connection.getBoardingPoint()).getStop();
                    dest = stopTimes.get(connection.getStopIndex()).getStop();

                    legs.add(new TimetableLeg(
                        origin,
                        dest,
                        connection.getStopTimes()
                    ));

                    dest = stopTimes.get(connection.getBoardingPoint()).getStop();
                }

                k--;
            }

            Collections.reverse(legs);

            results.add(new Journey(legs));
        }

        return results;
    }
}
