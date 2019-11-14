package com.raoulvdberge.raptor.model;

/**
 * Describes a leg that is based on a timetable, using a trip.
 *
 * @param <S> the stop
 */
public class TimetableLeg<S> extends Leg<S> {
    private final Trip<S> trip;

    public TimetableLeg(S origin, S destination, Trip<S> trip) {
        super(origin, destination);

        this.trip = trip;
    }

    /**
     * @return the trip
     */
    public Trip<S> getTrip() {
        return trip;
    }
}
