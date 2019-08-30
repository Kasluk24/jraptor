package com.raoulvdberge.raptor.model;

import java.util.List;
import java.util.Map;

public class TripAndTransferBuilderResult {
    private final List<RaptorTrip> trips;
    private final Map<RaptorStop, List<RaptorTransferLeg>> transfers;

    public TripAndTransferBuilderResult(List<RaptorTrip> trips, Map<RaptorStop, List<RaptorTransferLeg>> transfers) {
        this.trips = trips;
        this.transfers = transfers;
    }

    public List<RaptorTrip> getTrips() {
        return trips;
    }

    public Map<RaptorStop, List<RaptorTransferLeg>> getTransfers() {
        return transfers;
    }
}
