package com.raoulvdberge.raptor.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TripBuilder {
    private final List<Trip> trips = new ArrayList<>();
    private final Map<String, Stop> stopsByName = new HashMap<>();

    public class TripConfigurer {
        private final List<StopTime> stopTimes = new ArrayList<>();
        private boolean addedLastStop;

        public TripConfigurer stop(String stopName, LocalDateTime arrivalTime, LocalDateTime departureTime) {
            if (addedLastStop) {
                throw new TripBuilderException("Already added the last stop, can't add another one");
            }

            if (stopTimes.isEmpty()) {
                if (arrivalTime != LocalDateTime.MIN) {
                    throw new TripBuilderException("The first stop needs to arrive at 0");
                }

                if (departureTime == LocalDateTime.MIN) {
                    throw new TripBuilderException("The first stop can't depart at 0");
                }
            } else {
                if (arrivalTime == LocalDateTime.MIN) {
                    throw new TripBuilderException("Already added the first stop, we can't arrive at 0 again");
                }

                if (departureTime == LocalDateTime.MIN) {
                    addedLastStop = true;
                }
            }

            stopsByName.putIfAbsent(stopName, new Stop(stopName));

            stopTimes.add(new StopTime(stopsByName.get(stopName), arrivalTime, departureTime));

            return this;
        }
    }

    public TripBuilder trip(Consumer<TripConfigurer> configurerConsumer) {
        var tripConfigurer = new TripConfigurer();

        configurerConsumer.accept(tripConfigurer);

        if (tripConfigurer.stopTimes.isEmpty()) {
            throw new TripBuilderException("Trip has no stops");
        }

        if (tripConfigurer.stopTimes.get(tripConfigurer.stopTimes.size() - 1).getDepartureTime() != LocalDateTime.MIN) {
            throw new TripBuilderException("The last stop needs to depart at 0");
        }

        trips.add(new Trip(trips.size(), tripConfigurer.stopTimes));

        return this;
    }

    public List<Trip> build() {
        return trips;
    }
}
