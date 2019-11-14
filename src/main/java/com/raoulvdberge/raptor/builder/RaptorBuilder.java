package com.raoulvdberge.raptor.builder;

import com.raoulvdberge.raptor.model.StopTime;
import com.raoulvdberge.raptor.model.TransferLeg;
import com.raoulvdberge.raptor.model.impl.StopImpl;
import com.raoulvdberge.raptor.model.impl.TripImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Builds trips and transfers. These trips and transfers are contained in a {@link RaptorBuilderResult} instance.
 * This builder uses default implementations for stops and trips, {@link StopImpl} and {@link TripImpl}.
 */
public class RaptorBuilder {
    private final List<TripImpl> trips = new ArrayList<>();
    private final Map<String, StopImpl> stopsByName = new HashMap<>();
    private final Map<StopImpl, List<TransferLeg<StopImpl>>> transfers = new HashMap<>();

    public class TripConfigurer {
        private final List<StopTime<StopImpl>> stopTimes = new ArrayList<>();
        private boolean addedLastStop;

        /**
         * Adds a stop to the configured trip.
         *
         * @param stopName      the name of the stop
         * @param arrivalTime   the arrival time
         * @param departureTime the departure time
         * @return the configurer
         */
        public TripConfigurer stop(String stopName, LocalDateTime arrivalTime, LocalDateTime departureTime) {
            if (addedLastStop) {
                throw new RaptorBuilderException("Already added the last stop, can't add another one");
            }

            if (stopTimes.isEmpty()) {
                if (arrivalTime != LocalDateTime.MIN) {
                    throw new RaptorBuilderException("The first stop needs to arrive at 0");
                }

                if (departureTime == LocalDateTime.MIN) {
                    throw new RaptorBuilderException("The first stop can't depart at 0");
                }
            } else {
                if (arrivalTime == LocalDateTime.MIN) {
                    throw new RaptorBuilderException("Already added the first stop, we can't arrive at 0 again");
                }

                if (departureTime == LocalDateTime.MIN) {
                    addedLastStop = true;
                }
            }

            stopsByName.putIfAbsent(stopName, new StopImpl(stopName));

            stopTimes.add(new StopTime<>(stopsByName.get(stopName), arrivalTime, departureTime));

            return this;
        }
    }

    /**
     * Adds a transfer from stop A to stop B.
     *
     * @param from     name of stop A
     * @param to       name of stop B
     * @param duration the duration of this transfer
     * @return the builder
     */
    public RaptorBuilder transfer(String from, String to, Duration duration) {
        var fromStop = this.stopsByName.get(from);
        var toStop = this.stopsByName.get(to);

        this.transfers.putIfAbsent(fromStop, new ArrayList<>());
        this.transfers.get(fromStop).add(new TransferLeg<>(fromStop, toStop, duration, 0));

        return this;
    }

    /**
     * Adds a trip.
     *
     * @param configurerConsumer a consumer for a trip configurer
     * @return the builder
     */
    public RaptorBuilder trip(Consumer<TripConfigurer> configurerConsumer) {
        var tripConfigurer = new TripConfigurer();

        configurerConsumer.accept(tripConfigurer);

        if (tripConfigurer.stopTimes.isEmpty()) {
            throw new RaptorBuilderException("Trip has no stops");
        }

        if (tripConfigurer.stopTimes.get(tripConfigurer.stopTimes.size() - 1).getDepartureTime() != LocalDateTime.MIN) {
            throw new RaptorBuilderException("The last stop needs to depart at 0");
        }

        trips.add(new TripImpl(tripConfigurer.stopTimes));

        return this;
    }

    /**
     * Returns the trips and transfers in a {@link RaptorBuilderResult} instance.
     *
     * @return the result
     */
    public RaptorBuilderResult build() {
        return new RaptorBuilderResult(trips, transfers);
    }
}
