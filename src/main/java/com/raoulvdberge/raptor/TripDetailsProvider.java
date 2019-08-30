package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorRoute;
import com.raoulvdberge.raptor.model.RaptorTrip;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TripDetailsProvider {
    Optional<RaptorTrip> getEarliestTripAtStop(RaptorRoute route, int stopIndex, LocalDateTime time);
}
