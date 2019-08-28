package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.Route;
import com.raoulvdberge.raptor.model.Trip;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TripDetailsProvider {
    Optional<Trip> getEarliestTripAtStop(Route route, int stopIndex, LocalDateTime time);
}
