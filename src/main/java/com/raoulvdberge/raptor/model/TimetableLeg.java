package com.raoulvdberge.raptor.model;

public class TimetableLeg<S> extends Leg<S> {
    private final Trip<S> trip;

    public TimetableLeg(S origin, S destination, Trip<S> trip) {
        super(origin, destination);

        this.trip = trip;
    }

    public Trip<S> getTrip() {
        return trip;
    }
}
