package com.raoulvdberge.raptor.model;

import java.util.List;
import java.util.Map;

public class TripAndTransferBuilderResult {
    private final List<Trip> trips;
    private final Map<Stop, List<TransferLeg>> transfers;

    public TripAndTransferBuilderResult(List<Trip> trips, Map<Stop, List<TransferLeg>> transfers) {
        this.trips = trips;
        this.transfers = transfers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public Map<Stop, List<TransferLeg>> getTransfers() {
        return transfers;
    }
}
