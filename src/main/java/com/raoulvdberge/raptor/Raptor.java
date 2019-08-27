package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.*;

import java.time.LocalDateTime;
import java.util.*;

public class Raptor {
    private final Map<String, Map<Stop, Integer>> routeStopIndex;
    private final Map<String, List<Stop>> routePaths;
    private final Map<Stop, Set<String>> routesByStop;
    private final Map<String, List<Trip>> tripsByRoute;
    private final Map<Trip, List<StopTime>> tripStopTimes;
    private final Set<Stop> stops;

    public Raptor(Map<String, Map<Stop, Integer>> routeStopIndex,
                  Map<String, List<Stop>> routePaths,
                  Map<Stop, Set<String>> routesByStop,
                  Map<String, List<Trip>> tripsByRoute,
                  Map<Trip, List<StopTime>> tripStopTimes,
                  Set<Stop> stops) {
        this.routeStopIndex = routeStopIndex;
        this.routePaths = routePaths;
        this.routesByStop = routesByStop;
        this.tripsByRoute = tripsByRoute;
        this.tripStopTimes = tripStopTimes;
        this.stops = stops;
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
