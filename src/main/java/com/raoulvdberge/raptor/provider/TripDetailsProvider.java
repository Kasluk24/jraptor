package com.raoulvdberge.raptor.provider;

import com.raoulvdberge.raptor.model.Trip;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TripDetailsProvider<R, S> {
    Optional<Trip<S>> getEarliestTripAtStop(R route, int stopIndex, LocalDateTime time);
}
