package com.raoulvdberge.raptor.model;

import java.util.List;

/**
 * Describes a journey.
 *
 * @param <S> the stop
 */
public class Journey<S> {
    private final List<Leg<S>> legs;

    public Journey(List<Leg<S>> legs) {
        this.legs = legs;
    }

    /**
     * @return the legs of this journey
     */
    public List<Leg<S>> getLegs() {
        return legs;
    }
}
