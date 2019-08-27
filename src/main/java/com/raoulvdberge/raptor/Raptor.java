package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Raptor {
    private final Map<String, Map<Stop, Integer>> routeStopIndex = new HashMap<>();
    private final Map<String, List<Stop>> routePaths = new HashMap<>();
    private final Map<Stop, Set<String>> routesByStop = new HashMap<>();
    private final Map<String, List<Trip>> tripsByRoute = new HashMap<>();
    private final Map<Trip, List<StopTime>> tripStopTimes = new HashMap<>();
    private final Set<Stop> stops;

    public Raptor(List<Trip> trips) {
        for (var trip : trips) {
            var path = trip.getStopTimes().stream().map(StopTime::getStop).collect(Collectors.toList());
            var routeId = path.stream().map(Stop::getName).collect(Collectors.joining("->"));

            if (!this.routeStopIndex.containsKey(routeId)) {
                this.tripsByRoute.put(routeId, new ArrayList<>());
                this.routeStopIndex.put(routeId, new HashMap<>());
                this.routePaths.put(routeId, path);

                for (var i = 0; i < path.size(); ++i) {
                    this.routeStopIndex.get(routeId).put(path.get(i), i);
                    this.routesByStop.putIfAbsent(path.get(i), new HashSet<>());
                    this.routesByStop.get(path.get(i)).add(routeId);
                }
            }

            this.tripStopTimes.put(trip, trip.getStopTimes());
            this.tripsByRoute.get(routeId).add(trip);
        }

        this.stops = this.routesByStop.keySet();
    }

    public List<Journey> plan(String originName, String destinationName, LocalDateTime date) {
        return plan(
            this.stops.stream().filter(s -> s.getName().equals(originName)).findFirst().orElseThrow(),
            this.stops.stream().filter(s -> s.getName().equals(destinationName)).findFirst().orElseThrow(),
            date
        );
    }

    public List<Journey> plan(Stop origin, Stop destination, LocalDateTime date) {
        var kArrivals = new HashMap<Integer, Map<Stop, LocalDateTime>>();
        var kConnections = new HashMap<Stop, Map<Integer, KConnection>>();

        for (var stop : stops) {
            kConnections.put(stop, new HashMap<>());
        }

        var initialArrivals = new HashMap<Stop, LocalDateTime>();
        for (var stop : stops) {
            initialArrivals.put(stop, LocalDateTime.MAX);
        }
        kArrivals.put(0, initialArrivals);
        kArrivals.get(0).put(origin, LocalDateTime.MIN);

        var markedStops = new HashSet<Stop>();
        markedStops.add(origin);

        for (var k = 1; !markedStops.isEmpty(); ++k) {
            var queue = this.getQueue(markedStops);

            markedStops.clear();
            kArrivals.put(k, new HashMap<>(kArrivals.get(k - 1)));

            for (var entry : queue.entrySet()) {
                var boardingPoint = -1;
                var routeId = entry.getKey();
                var stop = entry.getValue();

                Optional<List<StopTime>> stopTimes = Optional.empty();

                for (var stopIndex = this.routeStopIndex.get(routeId).get(stop); stopIndex < this.routePaths.get(routeId).size(); ++stopIndex) {
                    var stopInRoute = this.routePaths.get(routeId).get(stopIndex);

                    if (stopTimes.isPresent() && stopTimes.get().get(stopIndex).getArrivalTime().isBefore(kArrivals.get(k).get(stopInRoute))) {
                        kArrivals.get(k).put(stopInRoute, stopTimes.get().get(stopIndex).getArrivalTime());
                        kConnections.get(stopInRoute).put(k, new KConnection(
                            stopTimes.get(),
                            boardingPoint,
                            stopIndex
                        ));

                        markedStops.add(stopInRoute);
                    }

                    if (stopTimes.isEmpty() || kArrivals.get(k - 1).get(stopInRoute).isBefore(stopTimes.get().get(stopIndex).getArrivalTime())) {
                        stopTimes = this.getEarliestTrip(routeId, stopIndex, kArrivals.get(k - 1).get(stopInRoute));

                        boardingPoint = stopIndex;
                    }
                }
            }
        }

        return this.getResults(kConnections, destination);
    }

    private Map<String, Stop> getQueue(Set<Stop> markedStops) {
        var queue = new HashMap<String, Stop>();

        for (var stop : markedStops) {
            for (var routeId : this.routesByStop.get(stop)) {
                if (!queue.containsKey(routeId) || this.isStopBefore(routeId, stop, queue.get(routeId))) {
                    queue.put(routeId, stop);
                }
            }
        }

        return queue;
    }

    private boolean isStopBefore(String routeId, Stop stopA, Stop stopB) {
        return this.routeStopIndex.get(routeId).get(stopA) < this.routeStopIndex.get(routeId).get(stopB);
    }

    private Optional<List<StopTime>> getEarliestTrip(String routeId, int stopIndex, LocalDateTime time) {
        var trip = this.tripsByRoute.get(routeId).stream().filter(t -> this.tripStopTimes.get(t).get(stopIndex).getDepartureTime().isAfter(time)).findFirst();

        if (trip.isPresent()) {
            return Optional.of(this.tripStopTimes.get(trip.get()));
        } else {
            return Optional.empty();
        }
    }

    private List<Journey> getResults(HashMap<Stop, Map<Integer, KConnection>> kConnections, Stop finalDestination) {
        var results = new ArrayList<Journey>();

        for (var k : kConnections.get(finalDestination).keySet()) {
            var dest = finalDestination;

            var legs = new ArrayList<Leg>();

            while (k > 0) {
                var connection = kConnections.get(dest).get(k);

                var stopTimes = connection.getStopTimes();

                var origin = stopTimes.get(connection.getBoardingPoint()).getStop();
                dest = stopTimes.get(connection.getStopIndex()).getStop();

                legs.add(new TimetableLeg(
                    origin,
                    dest,
                    connection.getStopTimes()
                ));

                dest = stopTimes.get(connection.getBoardingPoint()).getStop();

                k--;
            }

            Collections.reverse(legs);

            results.add(new Journey(legs));
        }

        return results;
    }
}
