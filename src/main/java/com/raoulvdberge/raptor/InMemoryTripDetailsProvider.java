package com.raoulvdberge.raptor;

import com.raoulvdberge.raptor.model.RaptorRoute;
import com.raoulvdberge.raptor.model.RaptorStopTime;
import com.raoulvdberge.raptor.model.RaptorTrip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTripDetailsProvider implements TripDetailsProvider {
    private final Map<RaptorRoute, List<RaptorTrip>> tripsByRoute;
    private final Map<RaptorTrip, List<RaptorStopTime>> tripStopTimes;

    public InMemoryTripDetailsProvider(Map<RaptorRoute, List<RaptorTrip>> tripsByRoute, Map<RaptorTrip, List<RaptorStopTime>> tripStopTimes) {
        this.tripsByRoute = tripsByRoute;
        this.tripStopTimes = tripStopTimes;
    }

    @Override
    public Optional<RaptorTrip> getEarliestTripAtStop(RaptorRoute route, int stopIndex, LocalDateTime time) {
        return this.tripsByRoute
            .get(route)
            .stream()
            .filter(t -> this.tripStopTimes.get(t).get(stopIndex).getDepartureTime().isAfter(time))
            .findFirst();
    }
}
